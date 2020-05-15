package com.nidaff.api.services;

import com.nidaff.api.dto.RoleDto;
import com.nidaff.entity.entities.Role;

public interface IRoleService {

    Role addRole(RoleDto roleDto);

    void deleteRoleByName(String roleName);

}
