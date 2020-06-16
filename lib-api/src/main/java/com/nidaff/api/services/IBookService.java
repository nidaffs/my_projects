package com.nidaff.api.services;

import com.nidaff.api.dto.BookDetailsDto;
import com.nidaff.api.dto.BookDto;
import com.nidaff.api.exceptions.BookAlreadyExistsException;
import com.nidaff.api.exceptions.SuchBookDoesNotExistException;

import java.util.List;

public interface IBookService {

    BookDto addBook(String isbn, String departmentName) throws BookAlreadyExistsException, SuchBookDoesNotExistException;

    BookDto getBookById(Long id);
    
    List<BookDetailsDto> searchBook(String query);
    
    List<BookDto> getBookByDepartmentId(Long id);

    void updateBook(Long id, BookDetailsDto bookDetailsDto, String quantity);

    public List<BookDto> getAllBooks();

    void deleteBookById(Long id);

}
