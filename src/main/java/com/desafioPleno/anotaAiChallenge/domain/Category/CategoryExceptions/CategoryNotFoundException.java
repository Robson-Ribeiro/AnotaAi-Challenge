package com.desafioPleno.anotaAiChallenge.domain.Category.CategoryExceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException() {
        super();
    }
    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(String message, Throwable error) {
        super(message, error);
    }
}
