package com.desafioPleno.anotaAiChallenge.ropositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.desafioPleno.anotaAiChallenge.domain.Category.CategoryEntity;

public interface CategoryRepository extends MongoRepository<CategoryEntity, String> {
}
