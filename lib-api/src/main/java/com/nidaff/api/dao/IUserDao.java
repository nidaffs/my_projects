package com.nidaff.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nidaff.entity.entities.User;

@Repository
public interface IUserDao extends JpaRepository<User, Long>{

	public User findUserByLogin(String login); 
}
