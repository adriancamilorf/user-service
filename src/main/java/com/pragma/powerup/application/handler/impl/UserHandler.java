package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.EmployeeRequestDto;
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
    public void saveEmployee(EmployeeRequestDto employeeRequestDto) {
        validateNotNull(employeeRequestDto);
        validateName(employeeRequestDto.getName());
        validateLastName(employeeRequestDto.getLastName());
        validateDocumentNumber(employeeRequestDto.getDocumentNumber());
        validatePhone(employeeRequestDto.getPhone());
        validateEmail(employeeRequestDto.getEmail());
        validatePassword(employeeRequestDto.getPassword());
        userServicePort.saveEmployee(userRequestMapper.toUserModel(employeeRequestDto));
    }


    @Override
    public UserResponse getUserById(Long id) {
        return userResponseMapper.toUserResponse(userServicePort.getUserById(id));
    }
}
