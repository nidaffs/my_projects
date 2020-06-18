package com.nidaff.rest.controllers;

import com.nidaff.api.dao.IUserDao;
import com.nidaff.api.dto.HistoryDto;
import com.nidaff.api.dto.UserDto;
import com.nidaff.api.services.IHistoryService;
import com.nidaff.api.services.IUserService;
import com.nidaff.entity.entities.User;
import com.nidaff.rest.utils.ImageFileUploader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    private static final String EXCEPTION = "exception";

    private static final String EXCEPTION2 = "exception2";

    private volatile Long principalId;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IUserService userService;

    @Autowired
    private IHistoryService historyService;

    @Autowired
    private ImageFileUploader imageFileUploader;

    @GetMapping(value = "user")
    public ModelAndView userPage(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            UserDto dto = userService.getUserByEmail(principal.getName());
            modelAndView.setViewName("userpage");
            modelAndView.addObject("dto", dto);
        } catch (EntityNotFoundException e) {
            modelAndView.addObject("em", e.getMessage());
            modelAndView.setViewName(EXCEPTION);
        }
        return modelAndView;
    }

    @PostMapping(value = "user")
    public ModelAndView saveUserChanges(Principal principal, UserDto dto,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        principalId = null;
        ModelAndView modelAndView = new ModelAndView();
        try {
            principalId = userService.getUserByEmail(principal.getName()).getId();
            userService.updateUser(principalId, dto);
            imageFileUploader.createOrUpdateImage(dto, file);
            modelAndView.setViewName("changessaved");
            User userHL = userDao.getOne(principalId);
            userHL.setHasLogo(true);
            userDao.save(userHL);
        } catch (EntityNotFoundException e1) {
            modelAndView.addObject("em", e1.getMessage());
            modelAndView.setViewName(EXCEPTION);
        } catch (IOException e) {
            modelAndView.setViewName("403");
        }
        return modelAndView;
    }

    @GetMapping(value = "history")
    public ModelAndView getUserHistory(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        List<HistoryDto> histories = historyService.getHistoryByUserId(principalId);
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
