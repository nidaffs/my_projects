package com.nidaff.api.services;

import com.nidaff.api.dto.UserDto;
import com.nidaff.entity.entities.BookRating;

public interface IBookRatingService {
	
	BookRating addBookRating(Long id, UserDto dto, Integer rating);

}