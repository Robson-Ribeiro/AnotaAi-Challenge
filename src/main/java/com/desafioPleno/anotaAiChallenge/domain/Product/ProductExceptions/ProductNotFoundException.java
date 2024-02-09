package com.desafioPleno.anotaAiChallenge.domain.Product.ProductExceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super();
    }
    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable error) {
        super(message, error);
    }
    
}
