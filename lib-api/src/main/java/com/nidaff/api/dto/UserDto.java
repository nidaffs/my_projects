package com.nidaff.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends ADto {

    private List<RoleDto> roles = new ArrayList<>();

    private List<HistoryDto> histories = new ArrayList<>();

    private String lastName;

    private String firstName;

    private String email;

    private String login;

    private String password;

}
