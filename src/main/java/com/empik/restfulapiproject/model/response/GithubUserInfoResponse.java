package com.empik.restfulapiproject.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class GithubUserInfoResponse extends BaseUserInfoResponse {

    @JsonProperty("followers")
    private Long numberOfFollowers;

    @JsonProperty("public_repos")
    private Long numberOfPublicRepositories;
}
