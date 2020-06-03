package com.nidaff.api.services;

import com.nidaff.api.dto.UserDto;
import com.nidaff.entity.entities.BookRating;

public interface IBookRatingService {

    void addBookRating(UserDto dto, String rate, Long id);

}
