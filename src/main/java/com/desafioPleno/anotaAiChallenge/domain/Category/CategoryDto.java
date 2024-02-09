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

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String ownerId;

    public CategoryDto(CategoryEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
