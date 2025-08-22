package com.douglasferdos.securitywithjwt.infrastructure.api;

import com.douglasferdos.securitywithjwt.application.service.AuthenticationService;
import com.douglasferdos.securitywithjwt.domain.model.User;
import com.douglasferdos.securitywithjwt.infrastructure.api.dto.LoginResponse;
import com.douglasferdos.securitywithjwt.infrastructure.api.dto.LoginUserDto;
import com.douglasferdos.securitywithjwt.infrastructure.api.dto.RegisterUserDto;
import com.douglasferdos.securitywithjwt.infrastructure.security.jwt.JwtService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(
        AuthenticationService authenticationService
    ) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signUp(registerUserDto);
                
        return ResponseEntity.ok(registeredUser);
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto loginUserDto) {
        return ResponseEntity.ok(authenticationService.authenticate(loginUserDto));
    }
    
}
