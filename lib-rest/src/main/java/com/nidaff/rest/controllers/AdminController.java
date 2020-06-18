package com.nidaff.rest.controllers;

import com.nidaff.api.dto.BookDto;
import com.nidaff.api.dto.HistoryDto;
import com.nidaff.api.dto.RoleDto;
import com.nidaff.api.dto.UserDto;
import com.nidaff.api.exceptions.DepartmentAlreadyExistsException;
import com.nidaff.api.services.IBookService;
import com.nidaff.api.services.IDepartmentService;
import com.nidaff.api.services.IHistoryService;
import com.nidaff.api.services.IRoleService;
import com.nidaff.api.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/")
public class AdminController {

    private static final String EXCEPTION2 = "exception2";

    private static final String EXCEPTION3 = "exception3";

    private static final String ID = "/{id}";

    @Autowired
    private IBookService bookService;

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IHistoryService historyService;

    @GetMapping(value = "histories")
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

    @GetMapping(value = "users")
    public ModelAndView getAllUsers() {
        ModelAndView modelAndView = new ModelAndView();
        List<UserDto> users = userService.getAllUsers();
        modelAndView.setViewName("users");
        List<RoleDto> roles = roleService.getAllRoles();
        modelAndView.addObject("roles", roles);
        modelAndView.addObject("userList", users);
        return modelAndView;
    }

    @GetMapping(value = "adddepartment")
    public ModelAndView addDepartment() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adddepartment");
        return modelAndView;
    }

    @PostMapping(value = "adddepartment")
    public ModelAndView addDepartment(String departmentName) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            departmentService.addDepartment(departmentName.toUpperCase());
            modelAndView.setViewName("departmentresult");
        } catch (DepartmentAlreadyExistsException e) {
            modelAndView.addObject("em", e.getMessage());
            modelAndView.setViewName(EXCEPTION2);
        }
        return modelAndView;
    }

    @GetMapping(value = "users/deleteuser/" + ID)
    public ModelAndView deleteUserOrNot(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            UserDto user;
            user = userService.getUserById(id);
            modelAndView.setViewName("deleteuser");
            modelAndView.addObject("user", user);
        } catch (EntityNotFoundException e) {
            modelAndView.addObject("em", e.getMessage());
            modelAndView.setViewName(EXCEPTION3);
        }
        return modelAndView;
    }

    @PostMapping(value = "users/deleteuser/" + ID)
    public ModelAndView deleteUser(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.deleteUserById(id);
            modelAndView.setViewName("userdeleted");
        } catch (EntityNotFoundException e) {
            modelAndView.addObject("em", e.getMessage());
            modelAndView.setViewName(EXCEPTION3);
        }
        return modelAndView;
    }

    @GetMapping(value = "/deletebook/" + ID)
    public ModelAndView deleteBookOrNot(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            BookDto book = bookService.getBookById(id);
            modelAndView.setViewName("deletebook");
            modelAndView.addObject("book", book);
        } catch (EntityNotFoundException e) {
            modelAndView.addObject("em", e.getMessage());
            modelAndView.setViewName(EXCEPTION3);
        }
        return modelAndView;
    }

    @PostMapping(value = "/deletebook/" + ID)
    public ModelAndView deleteBook(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            bookService.deleteBookById(id);
            modelAndView.setViewName("bookdeleted");
        } catch (EntityNotFoundException e) {
            modelAndView.addObject("em", e.getMessage());
            modelAndView.setViewName(EXCEPTION3);
        }
        return modelAndView;
    }

    @PostMapping(value = "/changerole" + ID)
    public ModelAndView changeUserRole(@PathVariable Long id, RoleDto roleDto) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            userService.changeUserRole(id, roleDto.getRoleName());
            modelAndView.setViewName("changessaved2");
        } catch (EntityNotFoundException e) {
            modelAndView.addObject("em", e.getMessage());
            modelAndView.setViewName(EXCEPTION2);
        }
        return modelAndView;
    }

    @GetMapping(value = "history" + ID)
    public ModelAndView getUserHistory(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        List<HistoryDto> histories = historyService.getHistoryByUserId(id);
        if (!histories.isEmpty()) {
            modelAndView.setViewName("userhistory");
            modelAndView.addObject("historyList", histories);
        } else {
            String msg = ("No one book is taken!");
            modelAndView.addObject("em", msg);
            modelAndView.setViewName(EXCEPTION2);
        }
        return modelAndView;
    }

}
