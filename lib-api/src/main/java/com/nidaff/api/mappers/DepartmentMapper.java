package com.nidaff.api.mappers;

import com.nidaff.api.dto.BookDto;
import com.nidaff.api.dto.DepartmentDto;
import com.nidaff.entity.entities.Book;
import com.nidaff.entity.entities.Department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentMapper {
    
    public static List<DepartmentDto> convertListDepartment(List<Department> entities) {
        List<DepartmentDto> departmentsDto = new ArrayList<>();
        for (Department entity : entities) {
            departmentsDto.add(entityToDtoDepartment(entity));
        }
        return departmentsDto;
    }

    public static DepartmentDto entityToDtoDepartment(Department entity) {
        DepartmentDto dto = new DepartmentDto();
        dto.setId(entity.getId());
        dto.setDepartmentName(entity.getDepartmentName());
        List<BookDto> booksDto = new ArrayList<>();
        for (Book book : entity.getBooks()) {
            booksDto.add(BookMapper.entityToBookDto(book));
        }
        dto.setBooks(booksDto);
        return dto;
    }

    public static Department dtoDepartmentToEntity(DepartmentDto dto) {
        Department department = new Department();
        department.setDepartmentName(dto.getDepartmentName());
        List<Book> books = new ArrayList<>();
        for (BookDto bookDto : dto.getBooks()) {
            books.add(BookMapper.dtoBookToEntity(bookDto));
        }
        department.setBooks(books);
        return department;
    }
    
}
