package com.desafioPleno.anotaAiChallenge.ropositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.desafioPleno.anotaAiChallenge.domain.Product.ProductEntity;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, String> {
}
