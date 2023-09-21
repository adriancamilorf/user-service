package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.response.RoleResponseDto;

public interface IRoleHandler {
    RoleResponseDto getRoleByUserId(Long id);
}
