package com.nidaff.api.dto;

import com.nidaff.entity.entities.Book;
import com.nidaff.entity.entities.User;

public class BookRatingDto extends ADto{
	
	private Integer rating;
	
	private User user;
	
	private Book book;

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}