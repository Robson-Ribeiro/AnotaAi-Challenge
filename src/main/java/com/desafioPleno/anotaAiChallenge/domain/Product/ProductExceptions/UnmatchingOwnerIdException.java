package com.desafioPleno.anotaAiChallenge.domain.Product.ProductExceptions;

public class UnmatchingOwnerIdException extends RuntimeException {
    public UnmatchingOwnerIdException() {
        super();
    }
    public UnmatchingOwnerIdException(String message) {
        super(message);
    }

    public UnmatchingOwnerIdException(String message, Throwable error) {
        super(message, error);
    }
    
}
