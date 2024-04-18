package com.example.CloneGamestop.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice(annotations = RestController.class)
@RestControllerAdvice
public class GlobalExceptionHandler {
//https://nikhilsukhani.medium.com/mastering-exception-handling-in-spring-boot-using-controlleradvice-and-exceptionhandler-e676b5dd62ed#:~:text=The%20%40ControllerAdvice%20annotation%20is%20used,for%20handling%20a%20specific%20exception.
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ConcreteErrorResponse> handleMyCustomException (RuntimeException ex) {
        ConcreteErrorResponse errorResponse = new ConcreteErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
