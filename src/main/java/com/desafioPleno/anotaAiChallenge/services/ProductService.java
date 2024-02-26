package com.desafioPleno.anotaAiChallenge.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.desafioPleno.anotaAiChallenge.ropositories.ProductRepository;
import com.desafioPleno.anotaAiChallenge.services.AWS.SnsService;
import com.desafioPleno.anotaAiChallenge.domain.Product.ProductDto;
import com.desafioPleno.anotaAiChallenge.domain.Product.ProductEntity;
import com.desafioPleno.anotaAiChallenge.domain.Product.ProductExceptions.PriceLessThanZeroException;
import com.desafioPleno.anotaAiChallenge.domain.Product.ProductExceptions.ProductNotFoundException;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    private final SnsService snsService;

    private final UserService userService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, SnsService snsService, UserService userService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.snsService = snsService;
        this.userService = userService;
    }

    public List<ProductDto> getAll() {
        Sort sort = Sort.by("id").ascending().and(
                Sort.by("ownerId").ascending()
            );

        List<ProductEntity> allProducts = productRepository.findAll(sort);
        return allProducts.stream().map(ProductDto::new).toList();
    }

    public ProductDto getById(String id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        return new ProductDto(productEntity);
    }

    public ProductDto create(ProductDto productDto) {

        if(productDto.getCategory() != null) {
            if(!productDto.getCategory().isEmpty()) {
                this.categoryService.getById(productDto.getCategory());
            }
        }

        if(productDto.getPrice() < 0) {
            throw new PriceLessThanZeroException("The product can't have a negative value as a price!");
        }

        userService.getUser(productDto.getOwnerId());

        productDto.setId(null);

        ProductEntity productEntity = new ProductEntity(productDto);
        productEntity = productRepository.save(productEntity);
        snsService.publish(productEntity.toString("create"));
        return new ProductDto(productEntity);
    }

    public ProductDto update(ProductDto productDto) {

        productRepository.findById(productDto.getId()).orElseThrow(ProductNotFoundException::new);

        if(productDto.getCategory() != null) {
            if(!productDto.getCategory().isEmpty()) {
                this.categoryService.getById(productDto.getCategory());
            }
        }

        if(productDto.getPrice() < 0) {
            throw new PriceLessThanZeroException("The product can't have a negative value as a price!");
        }

        userService.getUser(productDto.getOwnerId());

        ProductEntity productEntity = new ProductEntity(productDto);
        productEntity = productRepository.save(productEntity);
        snsService.publish(productEntity.toString("update"));
        return new ProductDto(productEntity);
    }

    public void delete(String id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productRepository.delete(productEntity);
        snsService.publish(productEntity.toString("delete"));
    }
}
