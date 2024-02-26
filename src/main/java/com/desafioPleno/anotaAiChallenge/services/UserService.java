package com.desafioPleno.anotaAiChallenge.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.desafioPleno.anotaAiChallenge.domain.User.UserDto;
import com.desafioPleno.anotaAiChallenge.domain.User.UserEntity;
import com.desafioPleno.anotaAiChallenge.ropositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity(userDto);
        return new UserDto(userRepository.save(userEntity));
    }

    public List<UserDto> getAllUsers() {
        Sort sort = Sort.by("id").ascending().and(
                Sort.by("userName").ascending()
            );

        List<UserEntity> allUsers = userRepository.findAll(sort);
        return allUsers.stream().map(UserDto::new).toList();
    }
}
