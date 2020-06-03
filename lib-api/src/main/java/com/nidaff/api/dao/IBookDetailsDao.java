package com.nidaff.api.dao;

import com.nidaff.entity.entities.BookDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookDetailsDao extends JpaRepository<BookDetails, Long> {

    public BookDetails findBookDetailsByIsbn(String isbn);
    
    List<BookDetails> findBookByTitleContaining(String query);
    
}
