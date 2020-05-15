package com.nidaff.service.services;

import com.nidaff.api.dao.IDepartmentDao;
import com.nidaff.api.dto.DepartmentDto;
import com.nidaff.api.mappers.DepartmentMapper;
import com.nidaff.api.services.IDepartmentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentService implements IDepartmentService {
    
    private static final Logger logger = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    private IDepartmentDao departmentDao;

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return DepartmentMapper.convertListDepartment(departmentDao.findAll());
    }

    // addDepartment and others
    
}
