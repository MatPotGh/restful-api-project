package com.empik.restfulapiproject.mapper;

import com.empik.restfulapiproject.model.entity.UserInfo;
import com.empik.restfulapiproject.model.response.GithubUserInfoResponse;
import com.empik.restfulapiproject.model.response.UserInfoResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class UserInfoMapper {

    private static final Long INITIAL_REQUEST_COUNTER = 0L;

    public UserInfo toEntity(String login) {
        if (login == null) {
            return null;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setLogin(login);
        userInfo.setRequestCount(INITIAL_REQUEST_COUNTER);
        return userInfo;
    }

    public UserInfoResponse toResponse(GithubUserInfoResponse userGithubInfo, BigDecimal calculations) {
        if (userGithubInfo == null) {
            return null;
        }
        return UserInfoResponse.builder()
                .id(userGithubInfo.getId())
                .login(userGithubInfo.getLogin())
                .name(userGithubInfo.getName())
                .type(userGithubInfo.getType())
                .avatarUrl(userGithubInfo.getAvatarUrl())
                .createdAt(userGithubInfo.getCreatedAt())
                .calculations(calculations)
                .build();
    }
}
