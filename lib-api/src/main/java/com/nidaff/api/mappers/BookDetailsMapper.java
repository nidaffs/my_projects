package com.nidaff.api.mappers;

import com.nidaff.api.dto.BookDetailsDto;
import com.nidaff.entity.entities.BookDetails;

import java.util.ArrayList;
import java.util.List;

public class BookDetailsMapper {

    public static List<BookDetailsDto> convertListDetails(List<BookDetails> entities) {
        List<BookDetailsDto> books = new ArrayList<>();
        for (BookDetails entity : entities) {
            books.add(entityToDetailsDto(entity));
        }
        return books;
    }

    public static BookDetailsDto entityToDetailsDto(BookDetails entity) {
        BookDetailsDto dto = new BookDetailsDto();
        dto.setId(entity.getId());
        dto.setIsbn(entity.getIsbn());
        dto.setTitle(entity.getTitle());
        dto.setAuthor(entity.getAuthor());
        dto.setImage(entity.getImage());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public static BookDetails dtoDetailsToEntity(BookDetailsDto dto) {
        BookDetails bookDetails = new BookDetails();
        bookDetails.setId(dto.getId());
        bookDetails.setIsbn(dto.getIsbn());
        bookDetails.setTitle(dto.getTitle());
        bookDetails.setAuthor(dto.getAuthor());
        bookDetails.setImage(dto.getImage());
        bookDetails.setDescription(dto.getDescription());
        return bookDetails;
    }

    private BookDetailsMapper() {
        throw new IllegalStateException("Utility class");
    }

}
