package com.pos.posapi.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ResponseUserDataDTO {
    private int userId;
    private String email;
    private String firstName;
    private String lastName;
    private int roleId;
    private String roleName;
    private String prefix;

}
