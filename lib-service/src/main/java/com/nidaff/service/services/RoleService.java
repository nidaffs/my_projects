package com.nidaff.service.services;

import com.nidaff.api.dao.IRoleDao;
import com.nidaff.api.dto.RoleDto;
import com.nidaff.api.mappers.RoleMapper;
import com.nidaff.api.services.IRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<RoleDto> getAllRoles() {
        return RoleMapper.convertListRole(roleDao.findAll());
    }

}
