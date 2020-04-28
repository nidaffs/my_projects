package com.nidaff.api.services;

import java.util.List;

import com.nidaff.api.dto.UserDto;
import com.nidaff.entity.entities.User;

public interface IUserService {

	UserDto getUserById(Long id);
	
	User getUserByLogin(String login);

	void updateUser(Long id, UserDto userDto);

	void deleteUserById(Long id);
	
	public List<UserDto> getAllUsers();

	UserDto addUser(UserDto userDto);
}