package com.empik.restfulapiproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class RestApiExceptionResponse {
    private String message;
    private int httpStatus;
}
