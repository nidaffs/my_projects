package com.nidaff.rest.controllers;

import com.nidaff.api.dto.BookDto;
import com.nidaff.api.dto.DepartmentDto;
import com.nidaff.api.services.IBookService;
import com.nidaff.api.services.IDepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;

import java.util.List;

@RestController
@RequestMapping(value = "/departments/")
public class DepartmentController {

    private static final String ID = "/{id}";

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private IBookService bookService;

    @GetMapping
    public ModelAndView getAllDepartments() {
        ModelAndView modelAndView = new ModelAndView();
        List<DepartmentDto> departments = departmentService.getAllDepartments();
        modelAndView.setViewName("departments");
        modelAndView.addObject("departments", departments);
        return modelAndView;
    }

    @GetMapping(value = ID)
    public ModelAndView booksFromDepartment(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<BookDto> dto = bookService.getBookByDepartmentId(id);
            if (!dto.isEmpty()) {
                modelAndView.setViewName("booksfromdepartment");
                modelAndView.addObject("dto", dto);
            } else {
                String msg = ("There are no books in this department!");
                modelAndView.addObject("em", msg);
                modelAndView.setViewName("exception");
            }
        } catch (EntityNotFoundException e) {
            modelAndView.addObject("em", e.getMessage());
            modelAndView.setViewName("exception");
        }
        return modelAndView;
    }

}
