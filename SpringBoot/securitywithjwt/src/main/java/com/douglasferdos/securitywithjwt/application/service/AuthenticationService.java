package com.douglasferdos.securitywithjwt.application.service;

import com.douglasferdos.securitywithjwt.infrastructure.api.dto.LoginResponse;
import com.douglasferdos.securitywithjwt.infrastructure.security.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.douglasferdos.securitywithjwt.domain.model.User;
import com.douglasferdos.securitywithjwt.domain.repository.UserRepository;
import com.douglasferdos.securitywithjwt.infrastructure.api.dto.LoginUserDto;
import com.douglasferdos.securitywithjwt.infrastructure.api.dto.RegisterUserDto;


@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        AuthenticationManager authenticationManager,
        JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public User signUp(RegisterUserDto input) {
        User user = new User.UserBuilder(
            input.getFullName(),
            input.getEmail(),
            passwordEncoder.encode(input.getPassword())
        ).build();
        
        return userRepository.save(user);
    }

    public LoginResponse authenticate(LoginUserDto input) {
        var auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                input.getEmail(),
                input.getPassword()
            )
        );

        var user = ((User) auth.getPrincipal());
        var jwtToken = jwtService.generateToken(user);

        return new LoginResponse().setToken(jwtToken);
    }
}