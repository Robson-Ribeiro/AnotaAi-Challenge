package com.desafioPleno.anotaAiChallenge.domain.Product;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
public class ProductEntity {
    
    @Id
    private String id;

    private String title; 
    private String description; 
    private float price; 
    private String category; 
    private String ownerId;
    
    ProductEntity(String title, String description, float price, String category, String ownerId) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.ownerId = ownerId;
    }

    public ProductEntity(ProductDto dto) {
        BeanUtils.copyProperties(dto, this);
    }
}
