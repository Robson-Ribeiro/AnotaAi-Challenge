package com.desafioPleno.anotaAiChallenge.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.desafioPleno.anotaAiChallenge.ropositories.ProductRepository;

import com.desafioPleno.anotaAiChallenge.domain.Product.ProductDto;
import com.desafioPleno.anotaAiChallenge.domain.Product.ProductEntity;
import com.desafioPleno.anotaAiChallenge.domain.Product.ProductExceptions.ProductNotFoundException;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
        ProductEntity productEntity = new ProductEntity(productDto);
        return new ProductDto(productRepository.save(productEntity));
    }

    public ProductDto update(ProductDto productDto) {
        productRepository.findById(productDto.getId()).orElseThrow(ProductNotFoundException::new);

        ProductEntity productEntity = new ProductEntity(productDto);
        return new ProductDto(productRepository.save(productEntity));
    }

    public void delete(String id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productRepository.delete(productEntity);
    }
}

