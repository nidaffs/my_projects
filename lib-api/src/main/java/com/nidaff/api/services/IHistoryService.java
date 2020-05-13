package com.nidaff.api.services;

import java.util.List;

import com.nidaff.api.dto.HistoryDto;
import com.nidaff.api.dto.UserDto;

public interface IHistoryService {

	HistoryDto getHistoryById(Long id);

	void updateHistory(Long id, HistoryDto historyDto);

	void deleteHistoryById(Long id);

	public List<HistoryDto> getAllHistories();

	HistoryDto addHistory(UserDto userDto, Long id);
}