package com.nidaff.rest.controllers;

import com.nidaff.api.dto.UserDto;
import com.nidaff.api.exceptions.UserAlreadyExistsException;
import com.nidaff.api.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(value = "/signup")
public class SignUpController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IUserService userService;

    @GetMapping()
    public ModelAndView signUp() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signup");
        modelAndView.addObject("dto", new UserDto());
        return modelAndView;
    }

    @PostMapping()
    public ModelAndView addUserSubmit(UserDto dto) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.addUser(dto);
            modelAndView.setViewName("userresult");
            setAuth(dto.getEmail(), dto.getPassword());
        } catch (UserAlreadyExistsException e) {
            modelAndView.addObject("em", e.getMessage());
            modelAndView.setViewName("exception");
        }
        return modelAndView;
    }

    private void setAuth(String email, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
    }

}
