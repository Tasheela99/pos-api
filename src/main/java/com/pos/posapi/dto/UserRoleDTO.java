package com.pos.posapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserRoleDTO {

    private int roleId;

    private String roleName;

    private String description;

    private boolean activeState;
}
