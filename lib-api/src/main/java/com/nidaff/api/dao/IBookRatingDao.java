package com.nidaff.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nidaff.entity.entities.BookRating;

public interface IBookRatingDao extends JpaRepository<BookRating, Long>{

}
