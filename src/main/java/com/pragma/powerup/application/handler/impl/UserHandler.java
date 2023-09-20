package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.NewUserRequestDto;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
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

    @Override
    public void saveOwner(NewUserRequestDto newUserRequestDto) {
        userValidate(newUserRequestDto);
        userServicePort.saveOwner(userRequestMapper.toUserModel(newUserRequestDto));
    }
}
