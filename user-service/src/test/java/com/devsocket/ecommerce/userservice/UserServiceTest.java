package com.devsocket.ecommerce.userservice;

import com.devsocket.ecommerce.userservice.dto.AuthRequest;
import com.devsocket.ecommerce.userservice.dto.AuthResponse;
import com.devsocket.ecommerce.userservice.dto.RegisterRequest;
import com.devsocket.ecommerce.userservice.entity.User;
import com.devsocket.ecommerce.userservice.repository.UserRepository;
import com.devsocket.ecommerce.userservice.security.JwtUtil;
import com.devsocket.ecommerce.userservice.service.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private AuthenticationManager authManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService userService;

    @BeforeEach
    void setup() {
        userService = new AuthService(userRepository, passwordEncoder, authManager, jwtUtil);
        when(jwtUtil.generateToken(any(String.class))).thenReturn("123456Token");
    }

    @Test
    void shouldCreateUserSuccessfully() {
        User user = new User(1L,"Test", "test@gmail.com", "password");
        when(userRepository.save(any(User.class))).thenReturn(user);
        AuthResponse created = userService.register(new RegisterRequest("Test", "password"));
        Assertions.assertEquals("123456Token", created.token());
    }

    @Test
    void shouldLoginUserSuccessfully() {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken("Test", "password");

        when(authManager.authenticate(any(Authentication.class)))
                .thenReturn(token);

        AuthResponse created = userService.login(new AuthRequest("Test", "password"));
        Assertions.assertEquals("123456Token", created.token());
    }
}
