package com.nidaff.api.mappers;

import java.util.ArrayList;
import java.util.List;

import com.nidaff.api.dto.RoleDto;
import com.nidaff.api.dto.UserDto;
import com.nidaff.entity.entities.Role;
import com.nidaff.entity.entities.User;

public class RoleMapper {
	public static List<RoleDto> convertListRole(List<Role> entities) {
		List <RoleDto> rolesDto = new ArrayList<>();
		for (Role entity : entities) {
			rolesDto.add(entityToRoleDto(entity));
		}
		return rolesDto;
	}

	public static RoleDto entityToRoleDto(Role entity) {
		RoleDto dto = new RoleDto();
		dto.setId(entity.getId());
		dto.setRoleName(entity.getRoleName());
		List<UserDto> usersDto = new ArrayList<>();
		for (User user : entity.getUsers()){
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setEmail(user.getEmail());
            userDto.setLogin(user.getLogin());
            userDto.setPassword(user.getPassword());
            usersDto.add(userDto);
		}
		dto.setUsersDto(usersDto);
		return dto;
	}

	public static Role dtoRoleToEntity(RoleDto dto) {
		Role role = new Role();
		role.setRoleName(dto.getRoleName());
		List<User> users = new ArrayList<>();
		for (UserDto userDto : dto.getUsersDto()){
            User user = new User();
            user.setId(userDto.getId());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setLogin(userDto.getLogin());
            user.setPassword(userDto.getPassword());
            users.add(user);
		}
		role.setUsers(users);
		return role;
	}
}