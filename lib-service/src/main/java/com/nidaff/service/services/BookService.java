package com.nidaff.service.services;

import com.nidaff.api.dao.IBookDao;
import com.nidaff.api.dao.IBookDetailsDao;
import com.nidaff.api.dao.IDepartmentDao;
import com.nidaff.api.dto.BookDetailsDto;
import com.nidaff.api.dto.BookDto;
import com.nidaff.api.exceptions.BookAlreadyExistsException;
import com.nidaff.api.exceptions.SuchBookDoesNotExistException;
import com.nidaff.api.mappers.BookDetailsMapper;
import com.nidaff.api.mappers.BookMapper;
import com.nidaff.api.services.IBookService;
import com.nidaff.api.utils.IEmailSender;
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

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService implements IBookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private static final String SUCH_BOOK_DOES_NOT_EXIST = "Such book does not exist!";

    @Autowired
    private IBookDao bookDao;

    @Autowired
    private IDepartmentDao departmentDao;

    @Autowired
    private IBookDetailsDao bookDetailsDao;

    @Autowired
    private DataScrapper dataScrapper;

    @Autowired
    private IEmailSender emailSender;

    @Override
    public List<BookDto> getAllBooks() {
        return BookMapper.convertListBook(bookDao.findAll());
    }

    @Override
    public BookDto addBook(String isbn, String departmentName) throws BookAlreadyExistsException, SuchBookDoesNotExistException {
        isbn = RegExUtils.replaceAll(isbn, "-", StringUtils.EMPTY).trim();
        BookDetails bookDetailsFromDao = bookDetailsDao.findBookDetailsByIsbn(isbn);
        if (bookDetailsFromDao != null) {
            Book bookFromDao = bookDao.getOne(bookDetailsFromDao.getId());
            List<Department> departments = bookFromDao.getDepartments();
            if (departments.contains(departmentDao.findByDepartmentName(departmentName))) {
                throw new BookAlreadyExistsException();
            }
            departments.add(departmentDao.findByDepartmentName(departmentName));
            bookFromDao.setDepartments(departments);
            bookFromDao.setQuantity(bookFromDao.getQuantity() + 1);
            BookDto bookDto = BookMapper.entityToBookDto(bookDao.save(bookFromDao));
            try {
                emailSender.sendEmailToAdmin(bookDto, departmentName);
            } catch (MessagingException e) {
                logger.info("Mail not sent!");
            }
            return bookDto;
        }
        BookDetails bookDetails = dataScrapper.getBookDetailsFromWeb(isbn);
        bookDetailsDao.save(bookDetails);
        Book book = new Book();
        book.setBookDetails(bookDetails);
        book.setQuantity(1);
        book.setAvgRating("0,0");
        List<Department> departments = new ArrayList<>();
        departments.add(departmentDao.findByDepartmentName(departmentName));
        book.setDepartments(departments);
        BookDto bookDto = BookMapper.entityToBookDto(bookDao.save(book));
        try {
            emailSender.sendEmailToAdmin(bookDto, departmentName);
        } catch (MessagingException e) {
            logger.info("Mail not sent!");
        }
        return bookDto;
    }

    @Override
    public BookDto getBookById(Long id) {
        return BookMapper.entityToBookDto(Optional.ofNullable(bookDao.findBookById(id))
                .orElseThrow(() -> new EntityNotFoundException(SUCH_BOOK_DOES_NOT_EXIST)));
    }

    @Override
    public void deleteBookById(Long id) {
        bookDao.delete(Optional.ofNullable(bookDao.findBookById(id))
                .orElseThrow(() -> new EntityNotFoundException(SUCH_BOOK_DOES_NOT_EXIST)));
    }

    @Override
    public void updateBook(Long id, BookDetailsDto bookDetailsDto, String quantity) {
        BookDetails existingBookDetails = Optional.ofNullable(bookDao.findBookById(id).getBookDetails())
                .orElseThrow(() -> new EntityNotFoundException(SUCH_BOOK_DOES_NOT_EXIST));
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

    @Override
    public List<BookDetailsDto> searchBook(String query) {
        return BookDetailsMapper.convertListDetails(Optional.ofNullable(bookDetailsDao.findBookByTitleContaining(query))
                .orElseThrow(() -> new EntityNotFoundException(SUCH_BOOK_DOES_NOT_EXIST)));
    }

    @Override
    public List<BookDto> getBookByDepartmentId(Long id) {
        return BookMapper.convertListBook(Optional.ofNullable(departmentDao.findDepartmentById(id).getBooks())
                .orElseThrow(() -> new EntityNotFoundException("Such department does not exist!")));
    }

}
