package com.nidaff.api.dto;

import com.nidaff.entity.entities.Book;
import com.nidaff.entity.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class HistoryDto extends ADto {

    private User user;

    private Book book;

    private String userFirstName;

    private String userLastName;

    private String userEmail;

    private String bookTitle;

    private String bookAuthor;

    private LocalDateTime dateFrom;

    private LocalDateTime dateTo;

    private boolean isTaken;

}
