package com.nidaff.service.services;

import com.nidaff.api.dao.IRoleDao;
import com.nidaff.api.dao.IUserDao;
import com.nidaff.api.dto.UserDto;
import com.nidaff.api.mappers.UserMapper;
import com.nidaff.api.services.IUserService;
import com.nidaff.entity.entities.Role;
import com.nidaff.entity.entities.User;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.transaction.Transactional;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

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
        if (StringUtils.isNotBlank(userDto.getFirstName())) {
            user.setFirstName(userDto.getFirstName());
        }
        if (StringUtils.isNotBlank(userDto.getLastName())) {
            user.setLastName(userDto.getLastName());
        }
        if (StringUtils.isNotBlank(userDto.getEmail())) {
            user.setEmail(userDto.getEmail());
        }
        if (StringUtils.isNotBlank(userDto.getLogin())) {
            user.setLogin(userDto.getLogin());
        }
        if (StringUtils.isNotBlank(userDto.getPassword())) {
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

    @Override
    public boolean userHasLogo(List<UserDto> users) {
        for (UserDto userDto : users) {
            String filePath = new StringBuilder("classpath:static/images/").append(userDto.getLogin()).append(".png")
                    .toString();
            try {
                ResourceUtils.getFile(filePath);
            } catch (FileNotFoundException e) {
                return false;
            }
        }
        return true;
    }

}
