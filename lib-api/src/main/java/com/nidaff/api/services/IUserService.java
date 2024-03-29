package com.nidaff.api.services;

import com.nidaff.api.dto.UserDto;
import com.nidaff.api.exceptions.UserAlreadyExistsException;
import com.nidaff.entity.entities.User;

import java.util.List;

public interface IUserService {

    UserDto getUserById(Long id);
    
    UserDto getUserByEmail(String email);

    void updateUser(Long id, UserDto userDto);

    void deleteUserById(Long id);

    List<UserDto> getAllUsers();

    User addUser(UserDto userDto) throws UserAlreadyExistsException;
    
    User addFacebookUser(UserDto userDto);
    
    void changeUserRole(Long id, String roleName);
    
}
