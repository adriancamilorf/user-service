package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.request.OwnerRequestDto;
import com.pragma.powerup.application.dto.response.UserResponse;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
import com.pragma.powerup.application.mapper.IUserResponseMapper;
import com.pragma.powerup.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.pragma.powerup.application.validation.UserValidate.*;


@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    @Override
    public void saveOwner(OwnerRequestDto ownerRequestDto) {
        validateNotNull(ownerRequestDto);
        validateName(ownerRequestDto.getName());
        validateLastName(ownerRequestDto.getLastName());
        validateDocumentNumber(ownerRequestDto.getDocumentNumber());
        validatePhone(ownerRequestDto.getPhone());
        validateEmail(ownerRequestDto.getEmail());
        validatePassword(ownerRequestDto.getPassword());
        birthdateValidate(ownerRequestDto.getBirthdate());
        userServicePort.saveOwner(userRequestMapper.toUserModel(ownerRequestDto));
    }

    @Override
    public void saveEmployee(UserRequestDto userRequestDto) {
        validateNotNull(userRequestDto);
        validateName(userRequestDto.getName());
        validateLastName(userRequestDto.getLastName());
        validateDocumentNumber(userRequestDto.getDocumentNumber());
        validatePhone(userRequestDto.getPhone());
        validateEmail(userRequestDto.getEmail());
        validatePassword(userRequestDto.getPassword());
        userServicePort.saveEmployee(userRequestMapper.toUserModel(userRequestDto));
    }

    @Override
    public void saveClient(UserRequestDto userRequestDto) {
        validateNotNull(userRequestDto);
        validateName(userRequestDto.getName());
        validateLastName(userRequestDto.getLastName());
        validateDocumentNumber(userRequestDto.getDocumentNumber());
        validatePhone(userRequestDto.getPhone());
        validateEmail(userRequestDto.getEmail());
        validatePassword(userRequestDto.getPassword());
        userServicePort.saveClient(userRequestMapper.toUserModel(userRequestDto));
    }


    @Override
    public UserResponse getUserById(Long id) {
        return userResponseMapper.toUserResponse(userServicePort.getUserById(id));
    }
}
