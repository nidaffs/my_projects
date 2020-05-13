package com.nidaff.entity.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book extends AEntity {

	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "avg_rating")
	private Double avgRating;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_details_id", referencedColumnName = "id")
	private BookDetails bookDetails;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "department_has_book", 
	joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "department_id", referencedColumnName = "id"))
	private List<Department> departments;
	
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<History> histories;
	
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<BookRating> ratings;

	public BookDetails getBookDetails() {
		return bookDetails;
	}

	public void setBookDetails(BookDetails bookDetails) {
		this.bookDetails = bookDetails;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public List<History> getHistories() {
		return histories;
	}

	public void setHistories(List<History> histories) {
		this.histories = histories;
	}

	public Double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(Double avgRating) {
		this.avgRating = avgRating;
	}

	public List<BookRating> getRatings() {
		return ratings;
	}

	public void setRatings(List<BookRating> ratings) {
		this.ratings = ratings;
	}
}