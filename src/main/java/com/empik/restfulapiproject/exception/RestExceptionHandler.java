package com.empik.restfulapiproject.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<RestApiExceptionResponse> handleException(RestApiException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(RestApiExceptionResponse.of(e.getMessage(), e.getHttpStatus().value()));
    }
}
