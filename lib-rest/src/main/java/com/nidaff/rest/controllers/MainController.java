package com.nidaff.rest.controllers;

import com.nidaff.api.dto.UserDto;
import com.nidaff.api.exceptions.SuchUserDoesNotExistException;
import com.nidaff.api.exceptions.UserAlreadyExistsException;
import com.nidaff.api.services.IUserService;
import com.nidaff.rest.utils.ImageFileUploader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;

import java.security.Principal;

@RestController
public class MainController {

    private volatile Long principalId;

    @Autowired
    ImageFileUploader imageFileUploader;

    @Autowired
    IUserService userService;

    @GetMapping(value = "/")
    public ModelAndView main(Principal principal) {
        principalId = null;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        if (principal != null) {
        UserDto dto;
            try {
               //TODO check user role: user or facebookuser
                principalId = userService.getUserByLogin(principal.getName()).getId();
                dto = userService.getUserById(principalId);
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
    
    @GetMapping(value = "/signup")
    public ModelAndView signUp() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signUp");
        modelAndView.addObject("dto", new UserDto());
        return modelAndView;
    }

    @PostMapping(value = "/signup")
    public ModelAndView addUserSubmit(UserDto dto, @RequestParam(value = "file", required = false) MultipartFile file) {
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

    @GetMapping(value = "/admin")
    public ModelAndView admin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
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
