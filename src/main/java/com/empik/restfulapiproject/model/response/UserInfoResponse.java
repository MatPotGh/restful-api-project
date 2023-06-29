package com.empik.restfulapiproject.model.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class UserInfoResponse extends BaseUserInfoResponse {

	@JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal calculations;
}
