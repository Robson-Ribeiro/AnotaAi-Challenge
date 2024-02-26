package com.desafioPleno.anotaAiChallenge.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.desafioPleno.anotaAiChallenge.domain.Category.CategoryExceptions.CategoryNotFoundException;
import com.desafioPleno.anotaAiChallenge.domain.Product.ProductExceptions.PriceLessThanZeroException;
import com.desafioPleno.anotaAiChallenge.domain.Product.ProductExceptions.ProductNotFoundException;
import com.desafioPleno.anotaAiChallenge.domain.User.UserExceptions.UserNotFoundException;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e) {

        return ResponseEntity.badRequest().body("We couldn't find the product that you informed!");
    }

    @ExceptionHandler(value = PriceLessThanZeroException.class)
    public ResponseEntity<String> handlePriceLessThanZeroException(PriceLessThanZeroException e) {

        return ResponseEntity.badRequest().body("You're not allowed to create a product with a negative price!");
    }

    @ExceptionHandler(value = CategoryNotFoundException.class)
    public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException e) {

        return ResponseEntity.badRequest().body("We couldn't find the category that you informed!");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<ObjectError> fieldErrorList = e.getBindingResult().getAllErrors();

        List<String> errorList = new ArrayList<>();

        fieldErrorList.forEach(error -> errorList.add(error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errorList);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {

        return ResponseEntity.badRequest().body("We couldn't find the user that you informed!");
    }
}
