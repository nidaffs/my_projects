package com.nidaff.api.dao;

import com.nidaff.entity.entities.BookRating;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRatingDao extends JpaRepository<BookRating, Long> {

    public BookRating findByUserIdAndBookId(Long userId, Long bookId);

}
