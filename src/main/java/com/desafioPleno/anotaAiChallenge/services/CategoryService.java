package com.desafioPleno.anotaAiChallenge.services;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.desafioPleno.anotaAiChallenge.domain.Category.CategoryDto;
import com.desafioPleno.anotaAiChallenge.domain.Category.CategoryEntity;
import com.desafioPleno.anotaAiChallenge.domain.Category.CategoryExceptions.CategoryNotFoundException;
import com.desafioPleno.anotaAiChallenge.domain.Product.ProductEntity;
import com.desafioPleno.anotaAiChallenge.ropositories.CategoryRepository;
import com.desafioPleno.anotaAiChallenge.ropositories.ProductRepository;
import com.desafioPleno.anotaAiChallenge.services.AWS.SnsService;

@Service
public class CategoryService {
    
    private final CategoryRepository categoryRepository;

    private final SnsService snsService;

    private final MongoTemplate mongoTemplate;

    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, SnsService snsService, MongoTemplate mongoTemplate, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.snsService = snsService;
        this.mongoTemplate = mongoTemplate;
        this.productRepository = productRepository;
    }

    public List<CategoryDto> getAll() {
        Sort sort = Sort.by("id").ascending().and(
                Sort.by("ownerId").ascending()
            );

        List<CategoryEntity> allCategories = categoryRepository.findAll(sort);
        return allCategories.stream().map(CategoryDto::new).toList();
    }

    public CategoryDto getById(String id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        return new CategoryDto(categoryEntity);
    }

    public CategoryDto create(CategoryDto categoryDto) {
        categoryDto.setId(null);
        CategoryEntity categoryEntity = new CategoryEntity(categoryDto);
        categoryEntity = categoryRepository.save(categoryEntity);
        snsService.publish(categoryEntity.toString("create"));
        return new CategoryDto(categoryEntity);
    }

    public CategoryDto update(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = new CategoryEntity(categoryDto);
        categoryEntity = categoryRepository.save(categoryEntity);
        snsService.publish(categoryEntity.toString("update"));
        return new CategoryDto(categoryEntity);
    }

    public void delete(String id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        categoryRepository.delete(categoryEntity);
        Query query = new Query();
        query.addCriteria(Criteria.where("ownerId").is(categoryEntity.getOwnerId()));
        List<ProductEntity> products = mongoTemplate.find(query, ProductEntity.class);
        products.forEach((product) -> {
            if(product.getCategory().intern() == categoryEntity.getId().intern()) {
                product.setCategory("");
                productRepository.save(product);
                snsService.publish(product.toString("update"));
            }
        });
        snsService.publish(categoryEntity.toString("delete"));
    }
}
