package com.nidaff.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;

import com.nidaff.api.dao.IDepartmentDao;
import com.nidaff.api.dto.DepartmentDto;
import com.nidaff.api.exceptions.DepartmentAlreadyExistsException;
import com.nidaff.api.mappers.DepartmentMapper;
import com.nidaff.entity.entities.Department;
import com.nidaff.service.services.DepartmentService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceTest {

    @Mock
    IDepartmentDao departmentDao;

    @Mock
    DepartmentMapper departmentMapper;

    @InjectMocks
    DepartmentService departmentService;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(departmentDao).isNotNull();
        assertThat(departmentService).isNotNull();
    }

    @Test
    public void getAllDepartmentsTest() {
        List<Department> departments = new ArrayList<>();
        Department department1 = new Department();
        department1.setId(1L);
        department1.setDepartmentName("AA");
        Department department2 = new Department();
        department2.setId(2L);
        department2.setDepartmentName("BB");
        Department department3 = new Department();
        department3.setId(3L);
        department3.setDepartmentName("CC");
        departments.add(department1);
        departments.add(department2);
        departments.add(department3);
        when(departmentDao.findAll()).thenReturn(departments);
        List<DepartmentDto> dtoList = departmentService.getAllDepartments();
        assertThat(departments.size() == dtoList.size()).isTrue();
        //verify(departmentMapper, times(departments.size())).entityToDtoMinDepartment(any(Department.class));
    }

    @Test
    public void addDepartmentTest() throws DepartmentAlreadyExistsException {
        Department department = new Department();
        department.setId(1L);
        department.setDepartmentName("AA");
        departmentService.addDepartment(department.getDepartmentName());
        verify(departmentDao, times(1)).save(department);
    }
}
