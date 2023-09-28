package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.NewUserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponse;
import com.pragma.powerup.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> saveOwner(@RequestBody NewUserRequestDto userRequestDto) {
        userHandler.saveOwner(userRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Collections.singletonMap("message", "user created successfully"));

    }

    @Operation(summary = "Get user by Id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "User founded by Id",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = UserResponse.class
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request. Field missing",
                    content = @Content
            )
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userHandler.getUserById(id));
    }

}
