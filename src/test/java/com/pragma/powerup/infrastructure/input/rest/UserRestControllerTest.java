package com.pragma.powerup.infrastructure.input.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.pragma.powerup.application.dto.request.NewUserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponse;
import com.pragma.powerup.application.handler.IUserHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

class UserRestControllerTest {

    @InjectMocks
    private UserRestController userRestController;

    @Mock
    private IUserHandler userHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveOwner() {
        NewUserRequestDto userRequestDto = new NewUserRequestDto();
        userRequestDto.setName("John");
        userRequestDto.setLastName("Doe");
        userRequestDto.setEmail("john.doe@example.com");
        doNothing().when(userHandler).saveOwner(userRequestDto);
        ResponseEntity<Map<String, String>> result = userRestController.saveOwner(userRequestDto);
        verify(userHandler, times(1)).saveOwner(userRequestDto);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertTrue(result.getBody().containsKey("message"));
        assertEquals("user created successfully", result.getBody().get("message"));
    }

    @Test
    void testGetUserById() {
        Long userId = 1L;

        UserResponse userResponse = new UserResponse();
        userResponse.setId(userId);
        userResponse.setName("John");
        userResponse.setLastName("Doe");
        userResponse.setEmail("john.doe@example.com");

        when(userHandler.getUserById(userId)).thenReturn(userResponse);

        ResponseEntity<UserResponse> result = userRestController.getUserById(userId);

        verify(userHandler, times(1)).getUserById(userId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(userResponse, result.getBody());
    }
}
