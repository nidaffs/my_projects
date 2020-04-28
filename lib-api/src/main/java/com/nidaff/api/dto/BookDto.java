package com.nidaff.api.dto;

import java.util.ArrayList;
import java.util.List;

public class BookDto extends ADto{
	
	private BookDetailsDto bookDetails;
	
	private Integer quantity;
	
	private List<DepartmentDto> departmentsDto;
	
	private List<HistoryDto> histories = new ArrayList<>();

	public BookDetailsDto getBookDetails() {
		return bookDetails;
	}

	public void setBookDetails(BookDetailsDto bookDetails) {
		this.bookDetails = bookDetails;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<DepartmentDto> getDepartmentsDto() {
		return departmentsDto;
	}

	public void setDepartmentsDto(List<DepartmentDto> departmentsDto) {
		this.departmentsDto = departmentsDto;
	}

	public List<HistoryDto> getHistories() {
		return histories;
	}

	public void setHistories(List<HistoryDto> histories) {
		this.histories = histories;
	}
}