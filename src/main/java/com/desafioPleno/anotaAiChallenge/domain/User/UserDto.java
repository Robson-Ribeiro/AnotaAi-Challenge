package com.desafioPleno.anotaAiChallenge.domain.User;

import org.springframework.beans.BeanUtils;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String id;
    @NotBlank(message = "User name must be provided.")
    private String username;
    @NotBlank(message = "User password must be provided.")
    private String password;

    public UserDto(UserEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
