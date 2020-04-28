package com.nidaff.api.services;

import java.util.List;

import com.nidaff.api.dto.DepartmentDto;

public interface IDepartmentService {

	//DepartmentDto addDepartment(String departmentName);

	//DepartmentDto getDepartmentById(Long id);

	//void updateDepartment(Long id, DepartmentDto DepartmentDto);

	public List<DepartmentDto> getAllDepartments();

	//void deleteDepartmentById(Long id);
}