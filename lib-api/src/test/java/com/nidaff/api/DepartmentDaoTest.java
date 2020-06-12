package com.nidaff.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.nidaff.api.dao.IDepartmentDao;
import com.nidaff.entity.entities.Department;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DepartmentDaoTest {
    
    @Autowired
    private IDepartmentDao departmentDao;
    
    @Test
    public void whenFindByDepartmentName_thenReturnDepartment() {
        Department department = new Department();
        department.setId(1L);
        department.setDepartmentName("departmentName");
        departmentDao.save(department);
        Department found = departmentDao.findByDepartmentName(department.getDepartmentName());
        assertThat(found.getDepartmentName()).isEqualTo(department.getDepartmentName());
    }
    
}
