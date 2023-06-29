package com.empik.restfulapiproject.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public abstract class BaseUserInfoResponse {

    private Long id;

    private String login;

    private String name;

    private String type;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    @JsonProperty("created_at")
    private String createdAt;
}
