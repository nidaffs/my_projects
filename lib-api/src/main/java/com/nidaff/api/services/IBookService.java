package com.nidaff.api.services;

import java.util.List;

import com.nidaff.api.dto.BookDetailsDto;
import com.nidaff.api.dto.BookDto;

public interface IBookService {

	BookDto addBook(String isbn, String departmentName);

	BookDto getBookById(Long id);

	void updateBook(Long id, BookDetailsDto bookDetailsDto);

	public List<BookDto> getAllBooks();

	void deleteBookById(Long id);
	
	//BookDto addBookDetailsToBook(long bookId, long bookDetailsId);
}
