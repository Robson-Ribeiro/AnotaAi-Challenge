package com.desafioPleno.anotaAiChallenge.ropositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.desafioPleno.anotaAiChallenge.domain.User.UserEntity;

public interface UserRepository extends MongoRepository<UserEntity, String> {
}
