package com.nidaff.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoleDto extends ADto {

    private List<UserDto> usersDto;

    private String roleName;

}
