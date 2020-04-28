package com.nidaff.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nidaff.entity.entities.Role;

@Repository
public interface IRoleDao extends JpaRepository<Role, Long>{
	
	Role findByRoleName(String roleName);
	
	Role deleteByRoleName(String roleName);
}