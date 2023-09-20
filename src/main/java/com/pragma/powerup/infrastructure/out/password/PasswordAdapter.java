package com.pragma.powerup.infrastructure.out.password;

import com.pragma.powerup.domain.spi.IPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PasswordAdapter implements IPasswordEncoder {

    private final PasswordEncoder passwordEncoder;
    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
