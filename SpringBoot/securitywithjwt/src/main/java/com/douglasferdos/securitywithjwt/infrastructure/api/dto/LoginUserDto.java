package com.douglasferdos.securitywithjwt.infrastructure.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginUserDto {
    @NotBlank(message = "Email is required")
    @Email(message = "Not a valid Email format")
    private String email;
    @NotBlank(message = "Password is required")
	private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}