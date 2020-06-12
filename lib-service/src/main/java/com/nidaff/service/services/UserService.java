package com.nidaff.service.services;

import com.nidaff.api.dao.IRoleDao;
import com.nidaff.api.dao.IUserDao;
import com.nidaff.api.dto.UserDto;
import com.nidaff.api.exceptions.UserAlreadyExistsException;
import com.nidaff.api.mappers.UserMapper;
import com.nidaff.api.services.IUserService;
import com.nidaff.entity.entities.Role;
import com.nidaff.entity.entities.User;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements IUserService {

    private static final String SUCH_USER_DOES_NOT_EXIST = "Such user does not exist!";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IRoleDao roleDao;

    public List<UserDto> getAllUsers() {
        return UserMapper.convertListUser(userDao.findAll());
    }

    public User addUser(UserDto userDto) throws UserAlreadyExistsException {
        if (userDao.findUserByEmail(userDto.getEmail()) != null) {
            throw new UserAlreadyExistsException();
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleDao.findByRoleName("ROLE_USER"));
        user.setRoles(roles);
        user.setHasLogo(false);
        return userDao.save(user);
    }

    public User addFacebookUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        List<Role> roles = new ArrayList<>();
        roles.add(roleDao.findByRoleName("ROLE_USER"));
        user.setRoles(roles);
        user.setHasLogo(false);
        return userDao.save(user);
    }
    
    @Override
    public void updateUser(Long id, UserDto userDto) {
        User user = findUserById(id);
        if (StringUtils.isNotBlank(userDto.getFirstName())) {
            user.setFirstName(userDto.getFirstName());
        }
        if (StringUtils.isNotBlank(userDto.getLastName())) {
            user.setLastName(userDto.getLastName());
        }
        if (StringUtils.isNotBlank(userDto.getEmail())) {
            user.setEmail(userDto.getEmail());
        }
        if (StringUtils.isNotBlank(userDto.getPassword())) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        userDao.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userDao.delete(findUserById(id));
    }

    @Override
    public UserDto getUserById(Long id) {
        return UserMapper.entityToUserDto(findUserById(id));
    }

    @Override
    public void changeUserRole(Long id, String roleName) {
        User existingUser = findUserById(id);
        List<Role> roles = existingUser.getRoles();
        roles.set(0, roleDao.findByRoleName(roleName));
        existingUser.setRoles(roles);
        userDao.save(existingUser);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return UserMapper.entityToUserDto(Optional.ofNullable(userDao.findUserByEmail(email))
                .orElseThrow(()-> new EntityNotFoundException(SUCH_USER_DOES_NOT_EXIST)));
    }

    private User findUserById(Long id) {
        return Optional.ofNullable(userDao.findUserById(id))
                .orElseThrow(()-> new EntityNotFoundException(SUCH_USER_DOES_NOT_EXIST));
    }
    
}
