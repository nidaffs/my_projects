package com.nidaff.api.mappers;

import com.nidaff.api.dto.HistoryDto;
import com.nidaff.api.dto.UserDto;
import com.nidaff.entity.entities.History;
import com.nidaff.entity.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static List<UserDto> convertListUser(List<User> entities) {
        List<UserDto> usersDto = new ArrayList<>();
        for (User entity : entities) {
            usersDto.add(entityToUserMinDto(entity));
        }
        return usersDto;
    }

    public static UserDto entityToUserDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setHasLogo(entity.isHasLogo());
        dto.setPassword(entity.getPassword());
        if (entity.getHistories() != null) {
            List<HistoryDto> historiesDto = new ArrayList<>();
            for (History history : entity.getHistories()) {
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
        user.setHasLogo(dto.isHasLogo());
        user.setPassword(dto.getPassword());
        if (dto.getHistories() != null) {
            List<History> histories = new ArrayList<>();
            for (HistoryDto historyDto : dto.getHistories()) {
                histories.add(HistoryMapper.dtoHistoryToEntity(historyDto));
            }
            user.setHistories(histories);
            return user;
        }
        return user;
    }

    public static UserDto entityToUserMinDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setHasLogo(entity.isHasLogo());
        dto.setPassword(entity.getPassword());
        return dto;
    }

    public static User dtoUserToMinEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setHasLogo(dto.isHasLogo());
        user.setPassword(dto.getPassword());
        return user;
    }

    private UserMapper() {
        throw new IllegalStateException("Utility class");
    }

}
