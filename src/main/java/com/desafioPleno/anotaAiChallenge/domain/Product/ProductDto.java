package com.desafioPleno.anotaAiChallenge.domain.Product;

import org.springframework.beans.BeanUtils;

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

    @NotBlank(message = "Product title must be provided.")
    private String title;
    @NotBlank(message = "Product description must be provided.")
    private String description;
    @NotNull(message = "Product price must be provided.")
    private float price;

    private String category;
    @NotBlank(message = "Product ownerId must be provided.")
    private String ownerId;

    public ProductDto(ProductEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
