package com.nidaff.api.dto;

import java.util.List;

public class RoleDto extends ADto{
	
	private List<UserDto> usersDto;

	private String roleName;

	public List<UserDto> getUsersDto() {
		return usersDto;
	}

	public void setUsersDto(List<UserDto> usersDto) {
		this.usersDto = usersDto;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}