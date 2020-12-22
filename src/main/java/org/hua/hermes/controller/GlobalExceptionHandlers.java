package org.hua.hermes.controller;

import lombok.extern.log4j.Log4j2;
import org.hua.hermes.exception.ResourceNotFoundException;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:nikosdelta@protonmail.com">Nick Dimitrakopoulos</a>
 */
@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandlers
{

    //Exception controller when @Valid fails
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex)
    {
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(x-> x.getDefaultMessage())
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(Map.of("errors",errors));
    }

    //Exception controller when @Validated fails
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintValidationException(ConstraintViolationException ex)
    {
        List<String> errors = ex
                .getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(Map.of("errors",errors));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex)
    {
        return ResponseEntity.notFound().build();
    }

    //Handles locking exceptions
    @ExceptionHandler(ConcurrencyFailureException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ConcurrencyFailureException ex)
    {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

}