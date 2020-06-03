package com.nidaff.rest.controllers;

import com.nidaff.api.dto.BookDetailsDto;
import com.nidaff.api.dto.BookDto;
import com.nidaff.api.dto.HistoryDto;
import com.nidaff.api.dto.UserDto;
import com.nidaff.api.exceptions.UserAlreadyExistsException;
import com.nidaff.api.services.IBookService;
import com.nidaff.api.services.IHistoryService;
import com.nidaff.api.services.IUserService;
import com.nidaff.rest.utils.ImageFileUploader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;

import java.security.Principal;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    ImageFileUploader imageFileUploader;

    @Autowired
    IUserService userService;

    @Autowired
    IHistoryService historyService;
    
    @Autowired
    IBookService bookService;

    @GetMapping(value = "/")
    public ModelAndView main(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        if (principal != null) {
            UserDto dto;
            try {
                dto = userService.getUserByEmail(principal.getName());
                modelAndView.addObject("dto", dto);
            } catch (EntityNotFoundException e) {
                modelAndView.addObject("em", e.getMessage());
                modelAndView.setViewName("exception");
            }
        }
        return modelAndView;
    }

    @GetMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping(value = "/search")
    public ModelAndView search(String query) {
        ModelAndView modelAndView = new ModelAndView();
        List<BookDetailsDto> books = bookService.searchBook(query);
        if (books !=null) {
            modelAndView.setViewName("searchresult");
            modelAndView.addObject("books", books);
        } else {
        String msg = ("Such book does not exist!");    
        modelAndView.addObject("em", msg);
        modelAndView.setViewName("exception");
        }
        return modelAndView;
    }

    @GetMapping(value = "/signup")
    public ModelAndView signUp() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signUp");
        modelAndView.addObject("dto", new UserDto());
        return modelAndView;
    }

    @PostMapping(value = "/signup")
    public ModelAndView addUserSubmit(UserDto dto) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.addUser(dto);
            modelAndView.setViewName("userresult");
        } catch (UserAlreadyExistsException e) {
            modelAndView.addObject("em", e.getMessage());
            modelAndView.setViewName("exception");
        }
        return modelAndView;
    }

    @GetMapping(value = "/histories")
    public ModelAndView getAllHistories() {
        ModelAndView modelAndView = new ModelAndView();
        List<HistoryDto> histories = historyService.getAllHistories();
        if (!histories.isEmpty()) {
            modelAndView.setViewName("histories");
            modelAndView.addObject("historyList", histories);
        } else {
            String msg = ("Nobody has taken a book yet. Be the first!");    
            modelAndView.addObject("em", msg);
            modelAndView.setViewName("exception");
            }
        return modelAndView;
    }

    @GetMapping(value = "/403")
    public ModelAndView error403() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("403");
        return modelAndView;
    }

    @GetMapping(value = "/404")
    public ModelAndView error404() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404");
        return modelAndView;
    }

}
