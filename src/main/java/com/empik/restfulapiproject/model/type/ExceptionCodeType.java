package com.empik.restfulapiproject.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCodeType {

    GITHUB_USER_NOT_FOUND("Github user with given login do not exist.", HttpStatus.NOT_FOUND),

    ILLEGAL_CALCULATION_ARGUMENT("Followers number for the user is equal to 0. Calculations can't be proceeded.",
            HttpStatus.NOT_ACCEPTABLE);

    private final String message;
    private final HttpStatus httpStatus;
}
