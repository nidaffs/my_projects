package com.nidaff.api.services;

import com.nidaff.api.dto.UserDto;

public interface IBookRatingService {

    void addBookRating(UserDto dto, String rate, Long id);

}
