package com.nidaff.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookRatingDto extends ADto {

    private Integer rating;

    private UserDto userDto;

    private BookDto bookDto;

}
