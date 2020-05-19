package com.nidaff.rest.controllers;

import com.nidaff.api.dto.UserDto;
import com.nidaff.api.services.IUserService;
import com.nidaff.rest.utils.ImageFileUploader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users/")
public class UserController {

    private static final String ID = "{id}";

    private volatile Long principalId;

    @Autowired
    IUserService userService;

    @Autowired
    ImageFileUploader imageFileUploader;

    @GetMapping
    public ModelAndView getAllUsers() {
        ModelAndView modelAndView = new ModelAndView();
        List<UserDto> users = userService.getAllUsers();
        modelAndView.setViewName("users");
        modelAndView.addObject("userList", users);
        boolean userHasLogo = userService.userHasLogo(users);
        modelAndView.addObject("hasHasLogo", userHasLogo);
        return modelAndView;
    }

    @GetMapping(value = ID)
    public ModelAndView userPage(Principal principal) {
        principalId = null;
        ModelAndView modelAndView = new ModelAndView();
        principalId = userService.getUserByLogin(principal.getName()).getId();
        modelAndView.setViewName("userpage");
        UserDto dto = userService.getUserById(principalId);
        modelAndView.addObject("dto", dto);
        return modelAndView;
    }

    @PostMapping(value = ID)
    public ModelAndView saveUserChanges(UserDto dto,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView();
        userService.updateUser(principalId, dto);
        try {
            imageFileUploader.createOrUpdateImage(dto, file);
            modelAndView.setViewName("changessaved");
        } catch (IOException e) {
            modelAndView.setViewName("403");
        }
        return modelAndView;
    }

    // User user = authorizedUser.getUserByUsername();
    // model.addAttribute("user", user);
    // return "welcome";
    // }

}
