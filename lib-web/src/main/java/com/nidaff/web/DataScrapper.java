package com.nidaff.web;

import com.nidaff.api.exceptions.SuchBookDoesNotExistException;
import com.nidaff.entity.entities.BookDetails;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DataScrapper {

    private static final Logger logger = LoggerFactory.getLogger(DataScrapper.class);
    
    private WebClient webclient = WebClientProvider.getDefaultWebClient();

    private static final String SEARCH_URL = "https://www.bookfinder.com/search/?author=&title=&lang=en&isbn=%s&new_used=*&destination=by&currency=USD&mode=basic&st=sr&ac=qr";

    private static final String IMAGE_URL = "https://pictures.abebooks.com/isbn/%s-us-300.jpg";

    public BookDetails getBookDetailsFromWeb(String isbn) throws SuchBookDoesNotExistException {
        isbn = RegExUtils.replaceAll(isbn, "-", StringUtils.EMPTY).trim();
        BookDetails bookDetails = new BookDetails();
        try {
            String url = String.format(SEARCH_URL, isbn);
            HtmlPage bookPage = webclient.getPage(url);
            HtmlElement bookExist = (HtmlElement) bookPage.getByXPath("//*[@id='header-section-context']/span").get(0);
            if (bookExist.getTextContent().equals("Search Error")) {
                throw new SuchBookDoesNotExistException();
            } else {
                HtmlElement title = (HtmlElement) bookPage.getByXPath("//span[@id='describe-isbn-title']").get(0);
                HtmlElement author = (HtmlElement) bookPage.getByXPath("//span[@itemprop='author']").get(0);
                if (bookPage.getByXPath("//div[@id='bookSummary']").isEmpty()) {
                    bookDetails.setDescription("Please, add the description");
                } else {
                    HtmlElement bookinfo = (HtmlElement) bookPage.getByXPath("//div[@id='bookSummary']").get(0);
                    bookDetails.setDescription(bookinfo.getTextContent());
                }
                bookDetails.setIsbn(isbn);
                bookDetails.setImage(String.format(IMAGE_URL, isbn));
                bookDetails.setTitle(title.getTextContent());
                bookDetails.setAuthor(author.getTextContent());
                return bookDetails;
           }
        } catch (FailingHttpStatusCodeException | IOException e) {
            logger.info("context", e);
            return bookDetails;
        }
    }

}
