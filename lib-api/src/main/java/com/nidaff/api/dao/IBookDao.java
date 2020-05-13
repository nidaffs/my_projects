package com.nidaff.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nidaff.entity.entities.Book;

@Repository
public interface IBookDao extends JpaRepository<Book, Long> {
	
	void deleteBookById(Long id);
	
	@Query(value = "SELECT AVG(br.rating) FROM book_rating br WHERE br.book_id = ?" , nativeQuery = true)
	Float getAvgRating(Long id);
}