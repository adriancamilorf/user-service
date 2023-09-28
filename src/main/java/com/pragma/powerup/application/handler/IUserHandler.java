package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.EmployeeRequestDto;
import com.pragma.powerup.application.dto.request.OwnerRequestDto;
import com.pragma.powerup.application.dto.response.UserResponse;

public interface IUserHandler {
    void saveOwner(OwnerRequestDto ownerRequestDto);

    void saveEmployee(EmployeeRequestDto employeeRequestDto);

    UserResponse getUserById(Long id);

}
