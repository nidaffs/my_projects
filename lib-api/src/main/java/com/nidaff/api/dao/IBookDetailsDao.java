package com.nidaff.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nidaff.entity.entities.BookDetails;

@Repository
public interface IBookDetailsDao extends JpaRepository<BookDetails, Long>{

	public BookDetails findBookDetailsByIsbn(String isbn); 
}