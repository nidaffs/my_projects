package com.nidaff.api.dto;

import java.time.LocalDateTime;

public class HistoryDto extends ADto{
	
	private UserDto userHistoryDto;

    private BookDto bookHistoryDto;
    
	private String userFirstName;
	
	private String userLastName;
	
	private String userEmail;
	
	private String bookTitle;

	private String bookAuthor;
	
	private LocalDateTime dateFrom;
	
	private LocalDateTime dateTo;

	private boolean isTaken;

	public UserDto getUserHistoryDto() {
		return userHistoryDto;
	}

	public void setUserHistoryDto(UserDto userHistoryDto) {
		this.userHistoryDto = userHistoryDto;
	}

	public BookDto getBookHistoryDto() {
		return bookHistoryDto;
	}

	public void setBookHistoryDto(BookDto bookHistoryDto) {
		this.bookHistoryDto = bookHistoryDto;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public LocalDateTime getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDateTime dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDateTime getDateTo() {
		return dateTo;
	}

	public void setDateTo(LocalDateTime dateTo) {
		this.dateTo = dateTo;
	}

	public boolean isTaken() {
		return isTaken;
	}

	public void setTaken(boolean isTaken) {
		this.isTaken = isTaken;
	}
}