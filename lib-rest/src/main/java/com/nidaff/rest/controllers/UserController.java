package com.nidaff.rest.controllers;

import com.nidaff.api.dao.IUserDao;
import com.nidaff.api.dto.BookDetailsDto;
import com.nidaff.api.dto.DepartmentDto;
import com.nidaff.api.dto.RoleDto;
import com.nidaff.api.dto.UserDto;
import com.nidaff.api.mappers.UserMapper;
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
        List<RoleDto> roles = roleService.getAllRoles();
        modelAndView.setViewName("users");
        modelAndView.addObject("userList", users);
        modelAndView.addObject("roles", roles);
        return modelAndView;
    }
    
    @PostMapping(value = "changerole")
    public ModelAndView changeUserRole(UserDto userDto, RoleDto roleDto) {
        ModelAndView modelAndView = new ModelAndView();
        userService.changeUserRole(userDto.getLogin(), roleDto.getRoleName());;
        modelAndView.setViewName("changessaved");
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
            User userHL = userDao.getOne(principalId);
            userHL.setHasLogo(true);
            userDao.save(userHL);
        } catch (IOException e) {
            modelAndView.setViewName("403");
        }
        return modelAndView;
    }

    @GetMapping(value = ID + "/deleteuser")
    public ModelAndView deleteUserOrNot(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        UserDto user = userService.getUserById(id);
        modelAndView.setViewName("deleteuser");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping(value = ID + "/deleteuser")
    public ModelAndView deleteUser(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        userService.deleteUserById(id);
        modelAndView.setViewName("userdeleted");
        return modelAndView;
    }

}
