package com.nidaff.api.mappers;

import java.util.ArrayList;
import java.util.List;

import com.nidaff.api.dto.HistoryDto;
import com.nidaff.api.dto.UserDto;
import com.nidaff.entity.entities.History;
import com.nidaff.entity.entities.User;

public class UserMapper {
	public static List<UserDto> convertListUser(List<User> entities) {
		List<UserDto> usersDto = new ArrayList<>();
		for (User entity : entities) {
			usersDto.add(entityToUserDto(entity));
		}
		return usersDto;
	}

	public static UserDto entityToUserDto(User entity) {
		UserDto dto = new UserDto();
		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setEmail(entity.getEmail());
		dto.setLogin(entity.getLogin());
		dto.setPassword(entity.getPassword());
		if (entity.getHistories() != null) {
		List<HistoryDto> historiesDto = new ArrayList<>();
		for (History history : entity.getHistories()){
			historiesDto.add(HistoryMapper.entityToHistoryDto(history));
		}
		dto.setHistories(historiesDto);
		return dto;
		}
		return dto;
	}

	public static User dtoUserToEntity(UserDto dto) {
		User user = new User();
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setEmail(dto.getEmail());
		user.setLogin(dto.getLogin());
		user.setPassword(dto.getPassword());
		if (dto.getHistories() != null) {
		List<History> histories = new ArrayList<>();
		for (HistoryDto historyDto : dto.getHistories()){
			histories.add(HistoryMapper.dtoHistoryToEntity(historyDto));
		}
		user.setHistories(histories);
		return user;
		}
		return user;
	}
}
