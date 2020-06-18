package com.nidaff.rest.controllers;

import com.nidaff.api.dto.BookDetailsDto;
import com.nidaff.api.dto.UserDto;
import com.nidaff.api.services.IBookService;
import com.nidaff.api.services.IHistoryService;
import com.nidaff.api.services.IUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;

import java.security.Principal;
import java.util.List;

@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private static final String EXCEPTION = "exception";

    @Autowired
    private IUserService userService;

    @Autowired
    private IHistoryService historyService;

    @Autowired
    private IBookService bookService;

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
                modelAndView.setViewName(EXCEPTION);
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
        if (!books.isEmpty()) {
            modelAndView.setViewName("searchresult");
            modelAndView.addObject("books", books);
        } else {
            String msg = ("Such book does not exist!");
            modelAndView.addObject("em", msg);
            modelAndView.setViewName(EXCEPTION);
        }
        return modelAndView;
    }

    @GetMapping(value = "/403")
    public ModelAndView error403() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("403");
        return modelAndView;
    }

    @Scheduled(cron ="0 */5 * * *  ?")
    public void sendEmailToUser() {
        try {
            historyService.getAllOverdueHistories();
        } catch (EntityNotFoundException e) {
            logger.info(e.getMessage());
        }
    }

}
