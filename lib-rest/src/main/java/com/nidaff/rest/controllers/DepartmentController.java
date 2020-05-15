package com.nidaff.rest.controllers;

import com.nidaff.api.dto.DepartmentDto;
import com.nidaff.api.services.IDepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(value = "/departments/")
public class DepartmentController {

    private static final String ID = "{id}";

    @Autowired
    IDepartmentService departmentService;

    @GetMapping
    public ModelAndView getAllDepartments() {
        ModelAndView modelAndView = new ModelAndView();
        List<DepartmentDto> departments = departmentService.getAllDepartments();
        modelAndView.setViewName("departments");
        modelAndView.addObject("departmentsList", departments);
        return modelAndView;
    }

//	@PostMapping(value = "add")
//	public ModelAndView addDepartmentSubmit(DepartmentDto depatmentDto) {
//		ModelAndView modelAndView = new ModelAndView();
//		DepartmentDto departmentDto = departmentService.addDepartment(depatmentDto.getDepartmentName());
//		modelAndView.setViewName("departmentresult");
//		modelAndView.addObject("department", departmentDto);
//		return modelAndView;
//	}

//	@GetMapping(value = "add")
//	public ModelAndView addBook() {
//		ModelAndView modelAndView = new ModelAndView();
//		//List<DepartmentDto> departments = bookService.getAllBooks();
//		modelAndView.setViewName("addbook");
//		modelAndView.addObject("departmentDto", new DepartmentDto());
//		return modelAndView;
//	}
    
}

