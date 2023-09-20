package com.pragma.powerup.domain.spi;

public interface IPasswordEncoder {
    String encodePassword(String password);
}
