package com.pragma.powerup.application.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String lastName;
    private String documentNumber;
    private String phone;
    private LocalDate birthdate;
    private String email;
    private RoleResponseDto roleResponseDto;
}
