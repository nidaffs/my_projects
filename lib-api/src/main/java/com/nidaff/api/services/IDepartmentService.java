package com.nidaff.api.services;

import com.nidaff.api.dto.DepartmentDto;
import com.nidaff.api.exceptions.DepartmentAlreadyExistsException;

import java.util.List;

public interface IDepartmentService {

    void addDepartment(String departmentName) throws DepartmentAlreadyExistsException;

    List<DepartmentDto> getAllDepartments();

}
