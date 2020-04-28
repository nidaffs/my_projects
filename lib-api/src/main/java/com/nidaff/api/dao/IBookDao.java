package com.nidaff.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nidaff.entity.entities.Book;

@Repository
public interface IBookDao extends JpaRepository<Book, Long> {
	
	void deleteBookById(Long id);
}