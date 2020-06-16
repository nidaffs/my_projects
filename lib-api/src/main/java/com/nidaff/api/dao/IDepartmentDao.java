package com.nidaff.api.dao;

import com.nidaff.entity.entities.Department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartmentDao extends JpaRepository<Department, Long> {

    Department findByDepartmentName(String departmentName);
    
    Department findDepartmentById(Long id);
    
}
