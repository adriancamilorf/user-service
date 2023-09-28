package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.request.OwnerRequestDto;
import com.pragma.powerup.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRestController {

    private final IUserHandler userHandler;

    @Operation(summary = "Add a owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "owner created", content = @Content),
            @ApiResponse(responseCode = "409", description = "owner already exists", content = @Content)
    })
    @PostMapping("/saveOwner")
    public ResponseEntity<Map<String, String>> saveOwner(@RequestBody OwnerRequestDto ownerRequestDto) {
        userHandler.saveOwner(ownerRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Collections.singletonMap("message", "owner created successfully"));

    }

    @Operation(summary = "Add a employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "employee created", content = @Content),
            @ApiResponse(responseCode = "409", description = "employee already exists", content = @Content)
    })
    @PostMapping("/saveEmployee")
    public ResponseEntity<Map<String, String>> saveEmployee(@RequestBody UserRequestDto userRequestDto) {
        userHandler.saveEmployee(userRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Collections.singletonMap("message", "employee created successfully"));

    }

    @Operation(summary = "Add a client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "client created", content = @Content),
            @ApiResponse(responseCode = "409", description = "client already exists", content = @Content)
    })
    @PostMapping("/saveClient")
    public ResponseEntity<Map<String, String>> saveClient(@RequestBody UserRequestDto userRequestDto) {
        userHandler.saveClient(userRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Collections.singletonMap("message", "client created successfully"));

    }

}
