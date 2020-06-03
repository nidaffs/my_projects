package com.nidaff.api.dao;

import com.nidaff.entity.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<User, Long> {

    public User findUserByEmail(String email);
    
    public User findUserById(Long id);
    
}
