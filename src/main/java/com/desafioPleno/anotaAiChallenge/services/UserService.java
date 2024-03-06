package com.desafioPleno.anotaAiChallenge.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.desafioPleno.anotaAiChallenge.domain.User.UserDto;
import com.desafioPleno.anotaAiChallenge.domain.User.UserEntity;
import com.desafioPleno.anotaAiChallenge.domain.User.UserExceptions.UserNotFoundException;
import com.desafioPleno.anotaAiChallenge.ropositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto createUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        UserEntity userEntity = new UserEntity(userDto);
        userRepository.save(userEntity);
        userEntity.setPassword("secret");
        return new UserDto(userEntity);
    }

    public List<UserDto> getAllUsers() {
        Sort sort = Sort.by("id").ascending().and(
                Sort.by("userName").ascending()
            );

        List<UserEntity> allUsers = userRepository.findAll(sort);
        return allUsers.stream().map(UserDto::new).toList();
    }

    public UserDto getUser(String id) {
        return new UserDto(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
    }
}
