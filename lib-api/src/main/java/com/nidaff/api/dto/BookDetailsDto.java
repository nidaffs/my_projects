package com.nidaff.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailsDto extends ADto {

    private String isbn;

    private String title;

    private String author;

    private String description;

    private String image;

    private List<BookDto> books;

}
