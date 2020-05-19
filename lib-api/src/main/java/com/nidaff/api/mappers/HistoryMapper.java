package com.nidaff.api.mappers;

import com.nidaff.api.dto.HistoryDto;
import com.nidaff.entity.entities.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryMapper {

    public static List<HistoryDto> convertListHistory(List<History> entities) {
        List<HistoryDto> historiesDto = new ArrayList<>();
        for (History entity : entities) {
            historiesDto.add(entityToHistoryDto(entity));
        }
        return historiesDto;
    }

    public static HistoryDto entityToHistoryDto(History entity) {
        HistoryDto dto = new HistoryDto();
        dto.setId(entity.getId());
        dto.setUserFirstName(entity.getUserFirstName());
        dto.setUserLastName(entity.getUserLastName());
        dto.setUserEmail(entity.getUserEmail());
        dto.setBookTitle(entity.getBookTitle());
        dto.setBookAuthor(entity.getBookAuthor());
        dto.setDateFrom(entity.getDateFrom());
        dto.setDateTo(entity.getDateTo());
        dto.setTaken(entity.isTaken());
        dto.setUser(entity.getUser());
        dto.setBook(entity.getBook());
        return dto;
    }

    public static History dtoHistoryToEntity(HistoryDto dto) {
        History history = new History();
        history.setUserFirstName(dto.getUserFirstName());
        history.setUserLastName(dto.getUserLastName());
        history.setUserEmail(dto.getUserEmail());
        history.setBookTitle(dto.getBookTitle());
        history.setBookAuthor(dto.getBookAuthor());
        history.setDateFrom(dto.getDateFrom());
        history.setDateTo(dto.getDateTo());
        history.setTaken(dto.isTaken());
        history.setUser(dto.getUser());
        history.setBook(dto.getBook());
        return history;
    }
    
}
