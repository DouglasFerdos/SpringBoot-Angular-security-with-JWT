package com.douglasferdos.securitywithjwt.infrastructure.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto {
    @Email(message = "Not a valid Email format")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
	private String password;
    @NotBlank(message = "Email is required")
    @Size(min = 8, message = "Full Name is too short")
    private String fullName;
}
