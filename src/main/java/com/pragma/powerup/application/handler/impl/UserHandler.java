package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.NewUserRequestDto;
import com.pragma.powerup.application.dto.response.RoleResponseDto;
import com.pragma.powerup.application.dto.response.UserResponse;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.IRoleResponseDtoMapper;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
import com.pragma.powerup.application.mapper.IUserResponseMapper;
import com.pragma.powerup.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.pragma.powerup.application.validation.UserValidate.userValidate;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    @Override
    public void saveOwner(NewUserRequestDto newUserRequestDto) {
        userValidate(newUserRequestDto);
        userServicePort.saveOwner(userRequestMapper.toUserModel(newUserRequestDto));
    }

    @Override
    public UserResponse getUserById(Long id) {
        return userResponseMapper.toUserResponse(userServicePort.getUserById(id));
    }
}
