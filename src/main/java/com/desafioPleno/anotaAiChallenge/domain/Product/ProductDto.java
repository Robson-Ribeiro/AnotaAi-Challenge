package com.desafioPleno.anotaAiChallenge.domain.Product;

import org.springframework.beans.BeanUtils;

import com.desafioPleno.anotaAiChallenge.domain.Category.CategoryDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {
    
    private String id;

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private float price;
    @NotBlank
    private String category;
    @NotBlank
    private String ownerId;

    public ProductDto(ProductEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
