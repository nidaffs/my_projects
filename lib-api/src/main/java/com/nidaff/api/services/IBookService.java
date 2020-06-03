package com.nidaff.api.services;

import com.nidaff.api.dto.BookDetailsDto;
import com.nidaff.api.dto.BookDto;

import java.util.List;

public interface IBookService {

    BookDto addBook(String isbn, String departmentName);

    BookDto getBookById(Long id);
    
    List<BookDetailsDto> searchBook(String query);

    void updateBook(Long id, BookDetailsDto bookDetailsDto, String quantity);

    public List<BookDto> getAllBooks();

    void deleteBookById(Long id);

}
