package com.desafioPleno.anotaAiChallenge.ropositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.desafioPleno.anotaAiChallenge.domain.Category.CategoryEntity;

@Repository
public interface CategoryRepository extends MongoRepository<CategoryEntity, String> {
}
