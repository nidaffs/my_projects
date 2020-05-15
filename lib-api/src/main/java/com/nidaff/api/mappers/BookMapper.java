package com.nidaff.api.mappers;

import com.nidaff.api.dao.IBookDao;
import com.nidaff.api.dto.BookDetailsDto;
import com.nidaff.api.dto.BookDto;
import com.nidaff.api.dto.DepartmentDto;
import com.nidaff.entity.entities.Book;
import com.nidaff.entity.entities.BookDetails;
import com.nidaff.entity.entities.Department;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {

    @Autowired
    private static IBookDao bookDao;

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
        dto.setQuantity(entity.getQuantity());
        dto.setBookDetails(entityToDetailsDto(entity.getBookDetails()));
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
        book.setQuantity(dto.getQuantity());
        book.setBookDetails(dtoDetailsToEntity(dto.getBookDetails()));
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

    public static List<BookDetailsDto> convertListDetails(List<BookDetails> entities) {
        List<BookDetailsDto> books = new ArrayList<>();
        for (BookDetails entity : entities) {
            books.add(entityToDetailsDto(entity));
        }
        return books;
    }

    public static BookDetailsDto entityToDetailsDto(BookDetails entity) {
        BookDetailsDto dto = new BookDetailsDto();
        dto.setId(entity.getId());
        dto.setIsbn(entity.getIsbn());
        dto.setTitle(entity.getTitle());
        dto.setAuthor(entity.getAuthor());
        dto.setImage(entity.getImage());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public static BookDetails dtoDetailsToEntity(BookDetailsDto dto) {
        BookDetails bookDetails = new BookDetails();
        bookDetails.setId(dto.getId());
        bookDetails.setIsbn(dto.getIsbn());
        bookDetails.setTitle(dto.getTitle());
        bookDetails.setAuthor(dto.getAuthor());
        bookDetails.setImage(dto.getImage());
        bookDetails.setDescription(dto.getDescription());
        return bookDetails;
    }
    
}
