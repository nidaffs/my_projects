package com.nidaff.service.services;

import com.nidaff.api.dao.IDepartmentDao;
import com.nidaff.api.dto.DepartmentDto;
import com.nidaff.api.exceptions.DepartmentAlreadyExistsException;
import com.nidaff.api.mappers.DepartmentMapper;
import com.nidaff.api.services.IDepartmentService;
import com.nidaff.entity.entities.Department;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentService implements IDepartmentService {

    @Autowired
    private IDepartmentDao departmentDao;

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return DepartmentMapper.convertListDepartment(departmentDao.findAll());
    }

    @Override
    public void addDepartment(String departmentName) throws DepartmentAlreadyExistsException {
        List<Department> departments = departmentDao.findAll();
        if (StringUtils.isNotBlank(departmentName)) {
            if (departments.contains(departmentDao.findByDepartmentName(departmentName))) {
                throw new DepartmentAlreadyExistsException();
            } else {
                Department department = new Department();
                department.setDepartmentName(departmentName);
                departments.add(departmentDao.save(department));
            }
        }
    }

}
