package com.empik.restfulapiproject.service;

import com.empik.restfulapiproject.exception.RestApiException;
import com.empik.restfulapiproject.model.response.GithubUserInfoResponse;
import com.empik.restfulapiproject.model.type.ExceptionCodeType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Slf4j
@Component
@RequiredArgsConstructor
public class GithubApiClient {

    private static final String GITHUB_USER_API = "%s/users/%s";

    @Value("${github.host}")
    private String githubHost;

    private final WebClient.Builder webClientBuilder;

    public GithubUserInfoResponse getUserInfoByGithubLogin(String login) {
        log.info("[getUserInfoByGithubLogin] invoked with login=[{}]", login);
        String url = GITHUB_USER_API.formatted(githubHost, login);
        GithubUserInfoResponse githubUserInfo;
        try {
            githubUserInfo = webClientBuilder
                    .build()
                    .get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(GithubUserInfoResponse.class)
                    .block();
        } catch (WebClientResponseException exception) {
            throw new RestApiException(ExceptionCodeType.GITHUB_USER_NOT_FOUND);
        }
        log.info("[getUserInfoByGithubLogin] received response=[{}]", githubUserInfo);
        return githubUserInfo;
    }
}
