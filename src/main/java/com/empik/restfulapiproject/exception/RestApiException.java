package com.empik.restfulapiproject.exception;

import com.empik.restfulapiproject.model.type.ExceptionCodeType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RestApiException extends RuntimeException {

    private final HttpStatus httpStatus;

    public RestApiException(ExceptionCodeType exceptionCodeType) {
        super(exceptionCodeType.getMessage());
        this.httpStatus = exceptionCodeType.getHttpStatus();
    }
}
