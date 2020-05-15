package com.nidaff.api.services;

import com.nidaff.api.dto.HistoryDto;
import com.nidaff.api.dto.UserDto;

import java.util.List;

public interface IHistoryService {

    HistoryDto getHistoryById(Long id);

    void updateHistory(Long id, HistoryDto historyDto);

    void deleteHistoryById(Long id);

    public List<HistoryDto> getAllHistories();

    HistoryDto addHistory(UserDto userDto, Long id);
    
}
