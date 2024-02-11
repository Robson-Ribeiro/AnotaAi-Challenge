package com.desafioPleno.anotaAiChallenge.services;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.desafioPleno.anotaAiChallenge.domain.Category.CategoryDto;
import com.desafioPleno.anotaAiChallenge.domain.Category.CategoryEntity;
import com.desafioPleno.anotaAiChallenge.domain.Category.CategoryExceptions.CategoryNotFoundException;
import com.desafioPleno.anotaAiChallenge.ropositories.CategoryRepository;

@Service
public class CategoryService {
    
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
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
        return new CategoryDto(categoryRepository.save(categoryEntity));
    }

    public CategoryDto update(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = new CategoryEntity(categoryDto);
        return new CategoryDto(categoryRepository.save(categoryEntity));
    }

    public void delete(String id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        categoryRepository.delete(categoryEntity);
    }
}
