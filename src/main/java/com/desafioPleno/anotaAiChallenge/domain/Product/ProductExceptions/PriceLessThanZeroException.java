package com.desafioPleno.anotaAiChallenge.domain.Product.ProductExceptions;

public class PriceLessThanZeroException extends RuntimeException {
    public PriceLessThanZeroException() {
        super();
    }
    
    public PriceLessThanZeroException(String message) {
        super(message);
    }

    public PriceLessThanZeroException(String message, Throwable error) {
        super(message, error);
    }
    
}
