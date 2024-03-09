package com.desafioPleno.anotaAiChallenge.ropositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.desafioPleno.anotaAiChallenge.domain.User.UserEntity;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
}
