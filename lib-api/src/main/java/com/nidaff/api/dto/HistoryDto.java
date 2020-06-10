package com.nidaff.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class HistoryDto extends ADto {

    private UserDto userDto;

    private BookDto bookDto;

    private String userFirstName;

    private String userLastName;

    private String userEmail;

    private String bookTitle;

    private String bookAuthor;

    private LocalDateTime dateFrom;

    private LocalDateTime dateTo;

    private boolean isTaken;
    
    private String department;
    
    public boolean getIsTaken() {
        return isTaken;
    }
    
}
