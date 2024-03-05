package com.pos.posapi.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserRoleDTO {
    private int roleId;
    private String roleName;

}