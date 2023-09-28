package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.request.OwnerRequestDto;
import com.pragma.powerup.application.dto.response.UserResponse;

public interface IUserHandler {
    void saveOwner(OwnerRequestDto ownerRequestDto);

    void saveEmployee(UserRequestDto userRequestDto);

    void saveClient(UserRequestDto userRequestDto);

    UserResponse getUserById(Long id);

}
