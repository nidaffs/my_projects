package com.nidaff.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nidaff.api.dto.BookDetailsDto;
import com.nidaff.api.dto.BookDto;
import com.nidaff.api.dto.DepartmentDto;
import com.nidaff.api.services.IBookService;
import com.nidaff.api.services.IDepartmentService;

@RestController
@RequestMapping(value = "/books/")
public class BookController {
	private static final String ID = "{id}";
	@Autowired
	IBookService bookService;
	
	@Autowired
	IDepartmentService departmentService;
	
	@GetMapping
	public ModelAndView getAllBooks() {
		ModelAndView modelAndView = new ModelAndView();
		List<BookDto> books = bookService.getAllBooks();
		modelAndView.setViewName("books");
		modelAndView.addObject("bookList", books);
		return modelAndView;
	}
	
	@GetMapping(value = ID)
	public ModelAndView getBook(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView();
		BookDto book = bookService.getBookById(id);
		//List <DepartmentDto> departments = book.getDepartmentsDto();
		modelAndView.setViewName("getbook");
		modelAndView.addObject("book", book);
		//modelAndView.addObject("departments", departments);
		return modelAndView;
	}
	
	@PostMapping(value = "add")
	public ModelAndView addBookSubmit(BookDetailsDto dto, DepartmentDto depatmentDto) {
		ModelAndView modelAndView = new ModelAndView();
		BookDto bookDto = bookService.addBook(dto.getIsbn(), depatmentDto.getDepartmentName());
		modelAndView.setViewName("result");
		modelAndView.addObject("book", bookDto);
		return modelAndView;
	}
	
	@GetMapping(value = "add")
	public ModelAndView addBook() {
		ModelAndView modelAndView = new ModelAndView();
		List <DepartmentDto> departments = departmentService.getAllDepartments();
		modelAndView.setViewName("addbook");
		modelAndView.addObject("dto", new BookDetailsDto());
		modelAndView.addObject("departments", departments);
		return modelAndView;
	}
//	@GetMapping
//  public String listBooks(Model model){
//      model.addAttribute("books", bookService.getAllBooks());
//      return "books";
//  }
	//
//	@GetMapping(value = "add")
//	public String addBook(Model model) {
//		model.addAttribute("dto", new BookDetailsDto());
//		return "addbook";
//	}
//	@PutMapping(value = ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public void updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
//		bookService.updateBook(id, bookDto);
//	}
//	@GetMapping(value = "{id}")
//	public BookDto getBook(@PathVariable int id) {
//		return bookService.getBookById(id);
//	}
//
//	@DeleteMapping(value = ID)
//	public void deleteDeveloper(@PathVariable Long id) {
//		bookService.deleteBookById(id);
//	}
}
