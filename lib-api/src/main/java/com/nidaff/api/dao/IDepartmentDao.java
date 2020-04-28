package com.nidaff.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nidaff.entity.entities.Department;

@Repository
public interface IDepartmentDao extends JpaRepository<Department, Long>{
	
	Department findByDepartmentName(String departmentName);
}
