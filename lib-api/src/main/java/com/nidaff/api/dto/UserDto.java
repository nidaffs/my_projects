package com.nidaff.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDto extends ADto {

    private List<RoleDto> roles = new ArrayList<>();

    private List<HistoryDto> histories = new ArrayList<>();

    private String lastName;

    private String firstName;

    private String email;

    private String login;

    private String password;
    
    private boolean hasLogo;

}
