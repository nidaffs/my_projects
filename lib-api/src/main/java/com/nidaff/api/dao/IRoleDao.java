package com.nidaff.api.dao;

import com.nidaff.entity.entities.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleDao extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);

}
