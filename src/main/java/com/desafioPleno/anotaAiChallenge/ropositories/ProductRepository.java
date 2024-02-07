package com.desafioPleno.anotaAiChallenge.ropositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.desafioPleno.anotaAiChallenge.domain.Product.ProductEntity;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {
}
