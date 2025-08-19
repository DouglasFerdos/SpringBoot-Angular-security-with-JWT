package com.douglasferdos.securitywithjwt.infrastructure.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDto {
    @Email(message = "Not a valid Email format")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
	private String password;
}
