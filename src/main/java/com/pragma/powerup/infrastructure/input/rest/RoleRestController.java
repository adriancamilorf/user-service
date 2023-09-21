package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.response.RoleResponseDto;
import com.pragma.powerup.application.dto.response.UserResponse;
import com.pragma.powerup.application.handler.impl.RoleHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleRestController {

    private final RoleHandler roleHandler;

    @Operation(summary = "Get role by userId")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "role founded by userId",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = RoleResponseDto.class
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
    public ResponseEntity<RoleResponseDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(roleHandler.getRoleByUserId(id));
    }
}
