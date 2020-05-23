package com.nidaff.api.services;

import com.nidaff.api.dto.UserDto;
import com.nidaff.entity.entities.User;

import java.util.List;

public interface IUserService {

    UserDto getUserById(Long id);

    User getUserByLogin(String login);

    void updateUser(Long id, UserDto userDto);

    void deleteUserById(Long id);

    public List<UserDto> getAllUsers();

    UserDto addUser(UserDto userDto);
    
    void changeUserRole(String login, String roleName);
}
