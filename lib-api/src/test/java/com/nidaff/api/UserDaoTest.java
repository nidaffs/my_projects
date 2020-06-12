package com.nidaff.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.nidaff.api.dao.IUserDao;
import com.nidaff.entity.entities.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDaoTest {
    
    @Autowired
    private IUserDao userDao;
    
    @Test
    public void whenFindByEmail_thenReturnUser() {
        User user = createUser();
        User found = userDao.findUserByEmail(user.getEmail());
        assertThat(found.getEmail()).isEqualTo(user.getEmail());
    }
    
    @Test
    public void whenFindById_thenReturnUser() {
        User user = createUser();
        User found = userDao.findUserById(user.getId());
        assertThat(found.getId()).isEqualTo(user.getId());
    }

    private User createUser() {
        User user = new User();
        user.setId(2L);
        user.setEmail("u@s.er");
        userDao.save(user);
        return user;
    }
    
}
