package com.nidaff.entity.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "history_user_book")
public class History extends AEntity {

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
	private User userHistory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book bookHistory;
    
    @Column(name = "user_first_name")
	private String userFirstName;
	
	@Column(name = "user_last_name")
	private String userLastName;
	
	@Column(name = "user_email")
	private String userEmail;
	
	@Column(name = "book_title")
	private String bookTitle;

	@Column(name = "book_author")
	private String bookAuthor;
	
	@Column(name = "date_from")
	private LocalDateTime dateFrom;
	
	@Column(name = "date_to")
	private LocalDateTime dateTo;

	@Column(name = "is_taken")
	private boolean isTaken;

	public User getUserHistory() {
		return userHistory;
	}

	public void setUserHistory(User userHistory) {
		this.userHistory = userHistory;
	}

	public Book getBookHistory() {
		return bookHistory;
	}

	public void setBookHistory(Book bookHistory) {
		this.bookHistory = bookHistory;
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