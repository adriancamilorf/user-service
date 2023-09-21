package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.NewUserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponse;

public interface IUserHandler {
    void saveOwner(NewUserRequestDto newUserRequestDto);
    UserResponse getUserById(Long id);

}
