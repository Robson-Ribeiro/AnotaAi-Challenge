package com.desafioPleno.anotaAiChallenge.domain.Category;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "categories")
@Getter
@Setter
@NoArgsConstructor
public class CategoryEntity {
    
    @Id
    private String id;

    private String title;
    private String description;
    private String ownerID;

    public CategoryEntity(String title, String description, String ownerID) {
        this.title = title;
        this.description = description;
        this.ownerID = ownerID;
    }

    public CategoryEntity(CategoryDto dto){
        BeanUtils.copyProperties(dto, this);
    }
}
