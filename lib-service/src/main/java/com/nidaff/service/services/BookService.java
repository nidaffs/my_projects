package com.nidaff.service.services;

import com.nidaff.api.dao.IBookDao;
import com.nidaff.api.dao.IBookDetailsDao;
import com.nidaff.api.dao.IDepartmentDao;
import com.nidaff.api.dto.BookDetailsDto;
import com.nidaff.api.dto.BookDto;
import com.nidaff.api.mappers.BookMapper;
import com.nidaff.api.services.IBookService;
import com.nidaff.entity.entities.Book;
import com.nidaff.entity.entities.BookDetails;
import com.nidaff.entity.entities.Department;
import com.nidaff.web.DataScrapper;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookService implements IBookService {
    
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    
    @Autowired
    private IBookDao bookDao;

    @Autowired
    private IDepartmentDao departmentDao;

    @Autowired
    private IBookDetailsDao bookDetailsDao;

    @Autowired
    private DataScrapper dataScrapper;

    @Override
    public List<BookDto> getAllBooks() {
        return BookMapper.convertListBook(bookDao.findAll());
    }

    @Override
    public BookDto addBook(String isbn, String departmentName) {
        isbn = RegExUtils.replaceAll(isbn, "-", StringUtils.EMPTY).trim();
        BookDetails bookDetailsFromDao = bookDetailsDao.findBookDetailsByIsbn(isbn);
        if (bookDetailsFromDao != null) {
            Book bookFromDao = bookDao.getOne(bookDetailsFromDao.getId());
            bookFromDao.setQuantity(bookFromDao.getQuantity() + 1);
            List<Department> departments = bookFromDao.getDepartments();
            // TODO: add department check here
            departments.add(departmentDao.findByDepartmentName(departmentName));
            bookFromDao.setDepartments(departments);
            return BookMapper.entityToBookDto(bookDao.save(bookFromDao));
        }
        BookDetails bookDetails = dataScrapper.getBookDetailsFromWeb(isbn);
        bookDetailsDao.save(bookDetails);
        Book book = new Book();
        // TODO: add конструктор в дто для букдетайлс и квантити
        book.setBookDetails(bookDetails);
        book.setQuantity(1);
        book.setAvgRating(0.0);
        List<Department> departments = new ArrayList<>();
        departments.add(departmentDao.findByDepartmentName(departmentName));
        book.setDepartments(departments);
        return BookMapper.entityToBookDto(bookDao.save(book));
    }

    @Override
    public BookDto getBookById(Long id) {
        BookDto dto = BookMapper.entityToBookDto(bookDao.getOne(id));
        if (bookDao.getAvgRating(id) != null) {
            String formatRating = new DecimalFormat("#0.0").format(bookDao.getAvgRating(id));
            dto.setAvgRating(formatRating);
            return dto;
        }
        dto.setAvgRating("0,0");
        return dto;
    }

    @Override
    public void deleteBookById(Long id) {
        bookDao.deleteBookById(id);
        logger.info("Book deleted");
    }

    @Override
    public void updateBook(Long id, BookDetailsDto bookDetailsDto, String quantity) {
        // BookDetails existingBookDetails =
        // Optional.ofNullable(bookDetailsDao.getOne(id)).orElseThrow(NoBookException)
        // если одновременно кто-то удалил книгу
        BookDetails existingBookDetails = bookDetailsDao.getOne(id);
        if (StringUtils.isNotBlank(bookDetailsDto.getAuthor())) {
            existingBookDetails.setAuthor(bookDetailsDto.getAuthor());
        }
        if (StringUtils.isNotBlank(bookDetailsDto.getDescription())) {
            existingBookDetails.setDescription(bookDetailsDto.getDescription());
        }
        if (StringUtils.isNotBlank(bookDetailsDto.getTitle())) {
            existingBookDetails.setTitle(bookDetailsDto.getTitle());
        }
        if (StringUtils.isNotBlank(quantity)) {
            existingBookDetails.getBooks().get(0).setQuantity(Integer.parseInt(quantity));
        }
        bookDetailsDao.save(existingBookDetails);
    }

}
