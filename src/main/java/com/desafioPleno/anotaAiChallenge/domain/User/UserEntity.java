package com.desafioPleno.anotaAiChallenge.domain.User;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    private String id;

    private String userName;
    private String password;

    public UserEntity(UserDto dto) {
        BeanUtils.copyProperties(dto, this);
    }
}
