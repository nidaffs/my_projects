package com.nidaff.api.services;

import com.nidaff.api.dto.HistoryDto;
import com.nidaff.api.dto.UserDto;
import com.nidaff.entity.entities.History;

import java.util.List;

public interface IHistoryService {

    HistoryDto getHistoryById(Long id, Long principalId);

    void updateHistory(Long id, Long principalId);

    void deleteHistoryById(Long id);

    public List<HistoryDto> getAllHistories();

    History addHistory(Long id, Long principalId);
    
    History returnBook(Long id, Long principalId);
    
}
