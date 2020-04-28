package com.nidaff.api.dto;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDto extends ADto{
	
	private List<BookDto> books = new ArrayList<>();
	
	private String departmentName;
	
	public List<BookDto> getBooks() {
		return books;
	}

	public void setBooks(List<BookDto> books) {
		this.books = books;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}