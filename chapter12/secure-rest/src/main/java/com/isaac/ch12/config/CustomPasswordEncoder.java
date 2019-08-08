package com.isaac.ch12.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * fix 'java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"'
 */
public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(rawPassword.toString());
    }
}
