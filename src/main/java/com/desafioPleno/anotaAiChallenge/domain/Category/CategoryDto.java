package com.desafioPleno.anotaAiChallenge.domain.Category;

import org.springframework.beans.BeanUtils;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    
    private String id;

    @NotBlank(message = "Category title must be provided.")
    private String title;
    @NotBlank(message = "Category description must be provided.")
    private String description;
    @NotBlank(message = "Category ownerId must be provided.")
    private String ownerId;

    public CategoryDto(CategoryEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
