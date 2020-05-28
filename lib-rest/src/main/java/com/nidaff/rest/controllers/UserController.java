package com.nidaff.rest.controllers;

import com.nidaff.api.dao.IUserDao;
import com.nidaff.api.dto.RoleDto;
import com.nidaff.api.dto.UserDto;
import com.nidaff.api.services.IRoleService;
import com.nidaff.api.services.IUserService;
import com.nidaff.entity.entities.User;
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

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users/")
public class UserController {

    private static final String ID = "{id}";

    private volatile Long principalId;
    
    @Autowired
    private IUserDao userDao;

    @Autowired
    IUserService userService;
    
    @Autowired
    IRoleService roleService;

    @Autowired
    ImageFileUploader imageFileUploader;

    @GetMapping
    public ModelAndView getAllUsers() {
        ModelAndView modelAndView = new ModelAndView();
        List<UserDto> users = userService.getAllUsers();
        modelAndView.setViewName("users");
        List<RoleDto> roles = roleService.getAllRoles();
        modelAndView.addObject("roles", roles);
        modelAndView.addObject("userList", users);
        return modelAndView;
    }
    
    @PostMapping(value = ID + "/changerole")
    public ModelAndView changeUserRole(@PathVariable Long id, RoleDto roleDto) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.changeUserRole(id, roleDto.getRoleName());
            modelAndView.setViewName("changessaved2");
        } catch (EntityNotFoundException e) {
            modelAndView.addObject("em", e.getMessage());
            modelAndView.setViewName("exception");
        }
        return modelAndView;
   }

    @GetMapping(value = ID)
    public ModelAndView userPage(Principal principal) {
        principalId = null;
        ModelAndView modelAndView = new ModelAndView();
        try {
            principalId = userService.getUserByLogin(principal.getName()).getId();
            UserDto dto = userService.getUserById(principalId);
            modelAndView.setViewName("userpage");
            modelAndView.addObject("dto", dto);
        } catch (EntityNotFoundException e) {
            modelAndView.addObject("em", e.getMessage());
            modelAndView.setViewName("exception");
        }
        return modelAndView;
    }

    @PostMapping(value = ID)
    public ModelAndView saveUserChanges(UserDto dto,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.updateUser(principalId, dto);
            try {
                imageFileUploader.createOrUpdateImage(dto, file);
                modelAndView.setViewName("changessaved");
                User userHL = userDao.getOne(principalId);
                userHL.setHasLogo(true);
                userDao.save(userHL);
            } catch (IOException e) {
                modelAndView.setViewName("403");
            }
        } catch (EntityNotFoundException e1) {
            modelAndView.addObject("em", e1.getMessage());
            modelAndView.setViewName("exception");
        }
        return modelAndView;
    }

    @GetMapping(value = ID + "/deleteuser")
    public ModelAndView deleteUserOrNot(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            UserDto user;
            user = userService.getUserById(id);
            modelAndView.setViewName("deleteuser");
            modelAndView.addObject("user", user);
        } catch (EntityNotFoundException e) {
            modelAndView.addObject("em", e.getMessage());
            modelAndView.setViewName("exception2");
        }
        return modelAndView;
    }

    @PostMapping(value = ID + "/deleteuser")
    public ModelAndView deleteUser(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.deleteUserById(id);
            modelAndView.setViewName("userdeleted");
        } catch (EntityNotFoundException e) {
            modelAndView.addObject("em", e.getMessage());
            modelAndView.setViewName("exception2");
        }
        return modelAndView;
    }

}
