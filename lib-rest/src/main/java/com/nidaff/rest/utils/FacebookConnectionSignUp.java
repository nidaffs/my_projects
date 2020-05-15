package com.nidaff.rest.utils;

import com.nidaff.api.dao.IUserDao;
import com.nidaff.api.dto.UserDto;
import com.nidaff.api.mappers.UserMapper;
import com.nidaff.api.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Component;

@Component
public class FacebookConnectionSignUp implements ConnectionSignUp {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserDao userDao;

    @Override
    public String execute(Connection<?> connection) {
        Facebook facebook = (Facebook) connection.getApi();
        String[] arrayFromFacebook = { "id", "email", "first_name", "last_name" };
        User userProfile = facebook.fetchObject("me", User.class, arrayFromFacebook);
        if (userDao.findUserByEmail(userProfile.getEmail()) != null) {
            UserDto user = UserMapper.entityToUserDto(userDao.findUserByEmail(userProfile.getEmail()));
            return user.getEmail();
        }
        UserDto user = new UserDto();
        user.setFirstName(userProfile.getFirstName());
        user.setLastName(userProfile.getLastName());
        user.setEmail(userProfile.getEmail());
        user.setPassword(userProfile.getId());
        user.setLogin("facebook");
        userService.addUser(user);
        return user.getEmail();
    }

}
