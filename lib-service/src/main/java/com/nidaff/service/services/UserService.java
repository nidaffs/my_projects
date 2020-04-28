package com.nidaff.service.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nidaff.api.dao.IRoleDao;
import com.nidaff.api.dao.IUserDao;
import com.nidaff.api.dto.UserDto;
import com.nidaff.api.mappers.UserMapper;
import com.nidaff.api.services.IUserService;
import com.nidaff.entity.entities.Role;
import com.nidaff.entity.entities.User;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IRoleDao roleDao;

	public List<UserDto> getAllUsers() {
		return UserMapper.convertListUser(userDao.findAll());
	}

	public UserDto addUser(UserDto userDto) {
		User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setLogin(userDto.getLogin());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleDao.findByRoleName("User"));
		user.setRoles(roles);  
		return UserMapper.entityToUserDto(userDao.save(user));
	}

	@Override
	public void updateUser(Long id, UserDto userDto) {
		User user = userDao.getOne(id);
		if (!StringUtils.isEmpty(userDto.getFirstName())) {
			user.setFirstName(userDto.getFirstName());
		}
		if (!StringUtils.isEmpty(userDto.getLastName())) {
			user.setLastName(userDto.getLastName());
		}
		if (!StringUtils.isEmpty(userDto.getEmail())) {
			user.setEmail(userDto.getEmail());
		}
		if (!StringUtils.isEmpty(userDto.getLogin())) {
			user.setLogin(userDto.getLogin());
		}
		if (!StringUtils.isEmpty(userDto.getPassword())) {
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		}
		userDao.save(user);
	}

	@Override
	public void deleteUserById(Long id) {
		userDao.delete(userDao.getOne(id));
	}
	

	@Override
	public UserDto getUserById(Long id) {
		return UserMapper.entityToUserDto(userDao.getOne(id));
	}

	@Override
	public User getUserByLogin(String login) {
		return userDao.findUserByLogin(login);
	}
}