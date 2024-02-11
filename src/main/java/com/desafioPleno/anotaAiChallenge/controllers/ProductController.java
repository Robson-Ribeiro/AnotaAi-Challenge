package com.desafioPleno.anotaAiChallenge.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafioPleno.anotaAiChallenge.domain.Product.ProductDto;
import com.desafioPleno.anotaAiChallenge.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/product")
@CrossOrigin
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok().body(this.productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(this.productService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody @Valid ProductDto ProductDto) {
        return ResponseEntity.ok().body(this.productService.create(ProductDto));
    }
    
    @PutMapping
    public ResponseEntity<ProductDto> update(@RequestBody @Valid ProductDto ProductDto) {
        return ResponseEntity.ok().body(this.productService.update(ProductDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        this.productService.delete(id);
        return ResponseEntity.ok().body("Product deleted with success!");
    }
}
