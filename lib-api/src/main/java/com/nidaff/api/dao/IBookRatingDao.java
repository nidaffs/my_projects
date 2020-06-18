package com.nidaff.api.dao;

import com.nidaff.entity.entities.BookRating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRatingDao extends JpaRepository<BookRating, Long> {

    public BookRating findByUserIdAndBookId(Long userId, Long bookId);

}
