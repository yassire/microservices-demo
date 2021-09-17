package com.microservices.demo.elastic.query.service.api.error.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ElasticQueryServiceErrorHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ElasticQueryServiceErrorHandler.class);

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handle(AccessDeniedException e) {
        LOG.error("Access denied exception {}", e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not allowed to access this resource");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handle(IllegalArgumentException e) {
        LOG.error("Illegal argument exception {}", e);
        return ResponseEntity.badRequest().body("Illegal argument exception");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handle(RuntimeException e) {
        LOG.error("Runtime exception {}", e);
        return ResponseEntity.badRequest().body("Runtime exception");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception e) {
        LOG.error("Server exception {}", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server exception");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handle(MethodArgumentNotValidException e) {
        LOG.error("Method argument not valid exception {}", e);
        Map<String, String> errors = new HashMap<String, String>();
        e.getBindingResult().getAllErrors().forEach(error -> errors.put(((FieldError)error).getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
