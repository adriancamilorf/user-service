package com.pragma.powerup.application.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequestDto {
    private String name;
    private String lastName;
    private String documentNumber;
    private String phone;
    private String email;
    private String password;
}
