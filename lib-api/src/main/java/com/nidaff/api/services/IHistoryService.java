package com.nidaff.api.services;

import com.nidaff.api.dto.DepartmentDto;
import com.nidaff.api.dto.HistoryDto;
import com.nidaff.entity.entities.History;

import java.util.List;

public interface IHistoryService {

    List<HistoryDto> getHistoryByUserId(Long id);

    void updateHistory(Long id, Long principalId);
    
    void getAllOverdueHistories();

    public List<HistoryDto> getAllHistories();

    History addHistory(Long id, Long principalId, DepartmentDto dto);
    
    History returnBook(Long id, Long principalId);
    
}
