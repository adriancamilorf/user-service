package com.pragma.powerup.infrastructure.out.password;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.pragma.powerup.infrastructure.out.password.PasswordAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

class PasswordAdapterTest {

    private PasswordEncoder passwordEncoder;
    private PasswordAdapter passwordAdapter;

    @BeforeEach
    void setUp() {
        passwordEncoder = mock(PasswordEncoder.class);
        passwordAdapter = new PasswordAdapter(passwordEncoder);
    }

    @Test
    void testEncodePassword() {
        String password = "mypassword";
        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        String result = passwordAdapter.encodePassword(password);
        verify(passwordEncoder, times(1)).encode(password);
        assertEquals(encodedPassword, result);
    }
}
