package com.pos.posapi.service;

import com.pos.posapi.dto.requestdto.RequestUserSaveDto;
import com.pos.posapi.dto.responsedto.ResponseUserDataDTO;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;

import java.io.IOException;

public interface UserService {

    void initializeUser() throws IOException;

    CommonResponseDTO verifyUser(int otp, String email);

    ResponseUserDataDTO getAllUserData(String token);

    CommonResponseDTO createUser(RequestUserSaveDto userDTO);

}
