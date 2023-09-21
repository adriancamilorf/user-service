package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.response.RoleResponseDto;
import com.pragma.powerup.application.dto.response.UserResponse;
import com.pragma.powerup.application.handler.IRoleHandler;
import com.pragma.powerup.application.handler.IUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleHandler implements IRoleHandler {
    private final IUserHandler userHandler;

    @Override
    public RoleResponseDto getRoleByUserId(Long id) {
        UserResponse user =  userHandler.getUserById(id);
        if (user!=null){
            return user.getRoleResponseDto();
        }
        return null;
    }
}
