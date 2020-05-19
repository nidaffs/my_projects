package com.nidaff.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookDetailsDto extends ADto {

    private String isbn;

    private String title;

    private String author;

    private String description;

    private String image;

    private List<BookDto> books;

}
