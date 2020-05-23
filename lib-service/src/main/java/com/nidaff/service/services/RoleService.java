package com.nidaff.service.services;

import com.nidaff.api.dao.IRoleDao;
import com.nidaff.api.dto.RoleDto;
import com.nidaff.api.mappers.RoleMapper;
import com.nidaff.api.services.IRoleService;
import com.nidaff.entity.entities.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    public Role addRole(RoleDto roleDto) {
        Role role = new Role();
        role.setRoleName(roleDto.getRoleName());
        return roleDao.save(role);
    }

    @Override
    public void deleteRoleByName(String roleName) {
        roleDao.deleteByRoleName(roleName);
    }

    @Override
    public List<RoleDto> getAllRoles() {
        return RoleMapper.convertListRole(roleDao.findAll());
    }
    
}
