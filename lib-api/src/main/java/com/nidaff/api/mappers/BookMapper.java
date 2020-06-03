package com.nidaff.api.mappers;

import com.nidaff.api.dto.BookDto;
import com.nidaff.api.dto.DepartmentDto;
import com.nidaff.entity.entities.Book;
import com.nidaff.entity.entities.Department;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {

    public static List<BookDto> convertListBook(List<Book> entities) {
        List<BookDto> booksDto = new ArrayList<>();
        for (Book entity : entities) {
            booksDto.add(entityToBookDto(entity));
        }
        return booksDto;
    }

    public static BookDto entityToBookDto(Book entity) {
        BookDto dto = new BookDto();
        dto.setId(entity.getId());
        dto.setAvgRating(entity.getAvgRating());
        dto.setQuantity(entity.getQuantity());
        dto.setBookDetails(BookDetailsMapper.entityToDetailsDto(entity.getBookDetails()));
        List<DepartmentDto> departmentsDto = new ArrayList<>();
        for (Department department : entity.getDepartments()) {
            DepartmentDto departmentDto = new DepartmentDto();
            departmentDto.setId(department.getId());
            departmentDto.setDepartmentName(department.getDepartmentName());
            departmentsDto.add(departmentDto);
        }
        dto.setDepartmentsDto(departmentsDto);
//		List<HistoryDto> historiesDto = new ArrayList<>();
//		for (History history : entity.getHistories()){
//			historiesDto.add(HistoryMapper.entityToHistoryDto(history));
//		}
//		dto.setHistories(historiesDto);
        return dto;
    }

    public static Book dtoBookToEntity(BookDto dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setQuantity(dto.getQuantity());
        book.setAvgRating(dto.getAvgRating());
        book.setBookDetails(BookDetailsMapper.dtoDetailsToEntity(dto.getBookDetails()));
        List<Department> departments = new ArrayList<>();
        for (DepartmentDto departmentDto : dto.getDepartmentsDto()) {
            Department department = new Department();
            department.setId(departmentDto.getId());
            department.setDepartmentName(departmentDto.getDepartmentName());
            departments.add(department);
        }
        book.setDepartments(departments);
//		List<History> histories = new ArrayList<>();
//		for (HistoryDto historyDto : dto.getHistories()){
//			histories.add(HistoryMapper.dtoHistoryToEntity(historyDto));
//		}
//		book.setHistories(histories);
        return book;
    }
    
}
