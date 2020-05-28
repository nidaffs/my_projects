package com.nidaff.rest.controllers;

import com.nidaff.api.dto.BookDetailsDto;
import com.nidaff.api.dto.BookDto;
import com.nidaff.api.dto.DepartmentDto;
import com.nidaff.api.dto.UserDto;
import com.nidaff.api.exceptions.SuchUserDoesNotExistException;
import com.nidaff.api.services.IBookRatingService;
import com.nidaff.api.services.IBookService;
import com.nidaff.api.services.IDepartmentService;
import com.nidaff.api.services.IHistoryService;
import com.nidaff.api.services.IUserService;
import com.nidaff.rest.utils.ImageFileUploader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/books/")
public class BookController {

    private volatile Long principalId;

    private static final String ID = "{id}";

    @Autowired
    IBookService bookService;

    @Autowired
    IHistoryService historyService;

    @Autowired
    IUserService userService;

    @Autowired
    IDepartmentService departmentService;

    @Autowired
    IBookRatingService bookRatingService;

    @Autowired
    ImageFileUploader imageFileUploader;

    @GetMapping
    public ModelAndView getAllBooks() {
        ModelAndView modelAndView = new ModelAndView();
        List<BookDto> books = bookService.getAllBooks();
        modelAndView.setViewName("books");
        modelAndView.addObject("bookList", books);
        return modelAndView;
    }

    @GetMapping(value = ID)
    public ModelAndView getBook(@PathVariable Long id) {
        principalId = null;
        ModelAndView modelAndView = new ModelAndView();
        BookDto book = bookService.getBookById(id);
        // List <DepartmentDto> departments = book.getDepartmentsDto();
        modelAndView.setViewName("getbook");
        modelAndView.addObject("book", book);
        // modelAndView.addObject("departments", departments);
        return modelAndView;
    }

    @GetMapping(value = "add")
    public ModelAndView addBook() {
        ModelAndView modelAndView = new ModelAndView();
        List<DepartmentDto> departments = departmentService.getAllDepartments();
        modelAndView.setViewName("addbook");
        modelAndView.addObject("dto", new BookDetailsDto());
        modelAndView.addObject("departments", departments);
        return modelAndView;
    }

    @PostMapping(value = "add")
    public ModelAndView addBookSubmit(BookDetailsDto dto, DepartmentDto depatmentDto) {
        ModelAndView modelAndView = new ModelAndView();
        BookDto bookDto = bookService.addBook(dto.getIsbn(), depatmentDto.getDepartmentName());
        modelAndView.setViewName("result");
        modelAndView.addObject("book", bookDto);
        return modelAndView;
    }

    @GetMapping(value = ID + "/update")
    public ModelAndView updateBook(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("updatebook");
        BookDetailsDto dto = bookService.getBookById(id).getBookDetails();
        modelAndView.addObject("dto", dto);
        return modelAndView;
    }

    @PostMapping(value = ID + "/update")
    public ModelAndView saveBookChanges(@PathVariable Long id, BookDetailsDto dto, String quantity,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView();
        bookService.updateBook(id, dto, quantity);
//      try {
//          imageFileUploader.createOrUpdateImage(dto, file);
//          modelAndView.setViewName("bookchanges");
//      } catch (IOException e) {
//          modelAndView.setViewName("403");
//      }
        modelAndView.setViewName("changessaved2");
        return modelAndView;
    }

    @PostMapping(value = ID + "/rate")
    public ModelAndView addBookRating(String rating, Principal principal, @PathVariable Long id) {
        principalId = null;
        ModelAndView modelAndView = new ModelAndView();
        try {
            principalId = userService.getUserByLogin(principal.getName()).getId();
            UserDto dto = userService.getUserById(principalId);
            bookRatingService.addBookRating(dto, rating, id);
            modelAndView.setViewName("changessaved2");
        } catch (EntityNotFoundException e) {
            modelAndView.addObject("em", e.getMessage());
            modelAndView.setViewName("exception2");
        }
        return modelAndView;
    }

    @PostMapping(value = ID + "/getbook")
    public ModelAndView addHistory(Principal principal, @PathVariable Long id) {
        principalId = null;
        ModelAndView modelAndView = new ModelAndView();
        try {
            principalId = userService.getUserByLogin(principal.getName()).getId();
            historyService.addHistory(id, principalId);
            modelAndView.setViewName("thebookistaken");
        } catch (EntityNotFoundException e) {
            modelAndView.addObject("em", e.getMessage());
            modelAndView.setViewName("exception2");
        }
        return modelAndView;
    }

    @PostMapping(value = ID + "/returnbook")
    public ModelAndView updateHistory(Principal principal, @PathVariable Long id) {
        principalId = null;
        ModelAndView modelAndView = new ModelAndView();
        try {
            principalId = userService.getUserByLogin(principal.getName()).getId();
            historyService.addHistory(id, principalId);
            modelAndView.setViewName("thebookisreturn");
        } catch (EntityNotFoundException e) {
            modelAndView.addObject("em", e.getMessage());
            modelAndView.setViewName("exception2");
        }
        return modelAndView;
    }

    @PostMapping(value = ID + "/extendbook")
    public ModelAndView extendBook(Principal principal, @PathVariable Long id) {
        principalId = null;
        ModelAndView modelAndView = new ModelAndView();
        try {
            principalId = userService.getUserByLogin(principal.getName()).getId();
            historyService.updateHistory(id, principalId);
            modelAndView.setViewName("bookextended");
        } catch (EntityNotFoundException e) {
            modelAndView.addObject("em", e.getMessage());
            modelAndView.setViewName("exception2");
        }
        
        return modelAndView;
    }

    @GetMapping(value = ID + "/deletebook")
    public ModelAndView deleteBookOrNot(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        BookDto book = bookService.getBookById(id);
        modelAndView.setViewName("deletebook");
        modelAndView.addObject("book", book);
        return modelAndView;
    }
    
    @PostMapping(value = ID + "/deletebook")
    public ModelAndView deleteBook(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        bookService.deleteBookById(id);
        modelAndView.setViewName("bookdeleted");
        return modelAndView;
    }

}
