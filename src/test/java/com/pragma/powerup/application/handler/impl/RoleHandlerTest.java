package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.response.RoleResponseDto;
import com.pragma.powerup.application.dto.response.UserResponse;
import com.pragma.powerup.application.handler.IUserHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RoleHandlerTest {

    @InjectMocks
    private RoleHandler roleHandler;

    @Mock
    private IUserHandler userHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRoleByUserId() {
        Long userId = 1L;
        UserResponse userResponse = new UserResponse();
        RoleResponseDto expectedRole = new RoleResponseDto();
        userResponse.setRoleResponseDto(expectedRole);
        when(userHandler.getUserById(userId)).thenReturn(userResponse);

        RoleResponseDto result = roleHandler.getRoleByUserId(userId);

        Mockito.verify(userHandler, Mockito.times(1)).getUserById(userId);
        assertNotNull(result);
        assertEquals(expectedRole, result);
    }


    @Test
    void testGetRoleByUserId_UserNotFound() {
        when(userHandler.getUserById(Mockito.anyLong())).thenReturn(null);
        Long userId = 1L;
        RoleResponseDto result = roleHandler.getRoleByUserId(userId);
        Mockito.verify(userHandler, Mockito.times(1)).getUserById(userId);
        assertNull(result);
    }
}
