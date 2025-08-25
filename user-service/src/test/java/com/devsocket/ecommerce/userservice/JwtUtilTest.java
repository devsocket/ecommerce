package com.devsocket.ecommerce.userservice;

import com.devsocket.ecommerce.userservice.security.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil(); // example constructor
    }

    @Test
    void shouldGenerateAndValidateToken() {
        String username = "testuser";
        String token = jwtUtil.generateToken(username);

        Assertions.assertNotNull(token);
        Assertions.assertTrue(jwtUtil.isTokenValid(token));
        Assertions.assertEquals(username, jwtUtil.extractUsername(token));
    }

    @Test
    void shouldFailValidationForInvalidToken() {
        String fakeToken = "invalid.token.value";
        Assertions.assertFalse(jwtUtil.isTokenValid(fakeToken));
    }
}