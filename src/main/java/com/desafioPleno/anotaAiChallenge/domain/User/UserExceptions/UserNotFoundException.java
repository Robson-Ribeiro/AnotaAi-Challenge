package com.desafioPleno.anotaAiChallenge.domain.User.UserExceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable error) {
        super(message, error);
    }
}
