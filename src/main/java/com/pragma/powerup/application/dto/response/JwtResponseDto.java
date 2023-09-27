package com.pragma.powerup.application.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponseDto {
    private String token;
}
