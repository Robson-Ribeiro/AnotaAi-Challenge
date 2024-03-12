package com.desafioPleno.anotaAiChallenge.security;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {
    @NotBlank(message = "User name must be provided.")
    private String username;
    @NotBlank(message = "User password must be provided.")
    private String password;
}
