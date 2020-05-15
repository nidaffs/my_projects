package com.nidaff.api.services;

import com.nidaff.api.dto.DepartmentDto;

import java.util.List;

public interface IDepartmentService {

    // DepartmentDto addDepartment(String departmentName);

    // DepartmentDto getDepartmentById(Long id);

    // void updateDepartment(Long id, DepartmentDto DepartmentDto);

    public List<DepartmentDto> getAllDepartments();

    // void deleteDepartmentById(Long id);

}
