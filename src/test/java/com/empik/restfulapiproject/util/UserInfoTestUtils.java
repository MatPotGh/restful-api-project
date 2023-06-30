package com.empik.restfulapiproject.util;

import com.empik.restfulapiproject.model.entity.UserInfo;
import com.empik.restfulapiproject.model.response.GithubUserInfoResponse;
import com.empik.restfulapiproject.model.response.UserInfoResponse;

import java.math.BigDecimal;

public class UserInfoTestUtils {

	public static GithubUserInfoResponse getGithubUserInfoResponse(Long numberOfFollowers) {
		return GithubUserInfoResponse.builder().id(383316L).name("").login("test123")
				.avatarUrl("https://avatars.githubusercontent.com/u/383316?v=4").createdAt("2010-09-01T10:39:12Z")
				.type("User").numberOfFollowers(numberOfFollowers).numberOfPublicRepositories(120L).build();
	}

	public static UserInfoResponse getUserInfoResponse() {
		return UserInfoResponse.builder().id(383316L).name("").login("test123")
				.avatarUrl("https://avatars.githubusercontent.com/u/383316?v=4").createdAt("2010-09-01T10:39:12Z")
				.type("User").calculations(BigDecimal.valueOf(11)).build();
	}

	public static UserInfo getUserInfo(Long requestCounter) {
		UserInfo userInfo = new UserInfo();
		userInfo.setLogin("test123");
		userInfo.setRequestCounter(requestCounter);
		return userInfo;
	}
}
