package com.casumo.videorental.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class RuntimeExceptionHandler {
    Map<String, Object> response = new HashMap<>();

    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class, HttpMessageNotReadableException.class })
    protected ResponseEntity<?> handle400(RuntimeException e) {
        response.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler({ NoResourceFoundException.class })
    protected ResponseEntity<?> handleNoResourceFoundException(NoResourceFoundException e) {
        response.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(value = { FilmOutOfStockException.class, RepeatedFilmReturnException.class })
    public ResponseEntity<?> handle409(RuntimeException e) {
        response.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

}
