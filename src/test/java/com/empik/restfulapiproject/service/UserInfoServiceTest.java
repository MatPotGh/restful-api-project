package com.empik.restfulapiproject.service;

import com.empik.restfulapiproject.exception.RestApiException;
import com.empik.restfulapiproject.mapper.UserInfoMapper;
import com.empik.restfulapiproject.model.entity.UserInfo;
import com.empik.restfulapiproject.model.response.GithubUserInfoResponse;
import com.empik.restfulapiproject.model.response.UserInfoResponse;
import com.empik.restfulapiproject.model.type.ExceptionCodeType;
import com.empik.restfulapiproject.repository.UserInfoRepository;
import com.empik.restfulapiproject.util.UserInfoTestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserInfoServiceTest {

	@Mock
	private GithubApiClient githubApiClient;

	@Mock
	private UserInfoRepository userInfoRepository;

	@Mock
	private UserInfoMapper userInfoMapper;

	@InjectMocks
	private UserInfoService userInfoService;

	@Test
	void getUserInfoByGithubLogin_shouldReturnExpectedResponse() {
		// given
		UserInfo userInfo = UserInfoTestUtils.getUserInfo(1L);
		UserInfoResponse expectedUserInfo = UserInfoTestUtils.getUserInfoResponse();
		GithubUserInfoResponse githubUserInfo = UserInfoTestUtils.getGithubUserInfoResponse(100L);

		// when
		when(githubApiClient.getUserInfoByGithubLogin(anyString())).thenReturn(githubUserInfo);
		when(userInfoRepository.findByLogin(anyString())).thenReturn(Optional.of(userInfo));
		when(userInfoRepository.save(any(UserInfo.class))).thenReturn(userInfo);
		when(userInfoMapper.toResponse(any(GithubUserInfoResponse.class), any(BigDecimal.class)))
				.thenReturn(expectedUserInfo);

		// then
		UserInfoResponse actualUserInfo = userInfoService.getUserInfoByGithubLogin("test123");
		assertEquals(expectedUserInfo, actualUserInfo);
	}

	@Test
	void getUserInfoByGithubLogin_shouldThrowRestApiException() {
		// given
		UserInfo userInfo = UserInfoTestUtils.getUserInfo(1L);
		GithubUserInfoResponse githubUserInfo = UserInfoTestUtils.getGithubUserInfoResponse(0L);

		// when
		when(githubApiClient.getUserInfoByGithubLogin(anyString())).thenReturn(githubUserInfo);
		when(userInfoRepository.findByLogin(anyString())).thenReturn(Optional.of(userInfo));

		// then
		RestApiException e = assertThrows(RestApiException.class,
				() -> userInfoService.getUserInfoByGithubLogin("test123"));
		assertEquals(ExceptionCodeType.ILLEGAL_CALCULATION_ARGUMENT.getMessage(), e.getMessage());
	}
}
