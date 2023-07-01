package com.empik.restfulapiproject.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.empik.restfulapiproject.exception.RestApiException;
import com.empik.restfulapiproject.mapper.UserInfoMapper;
import com.empik.restfulapiproject.model.entity.UserInfo;
import com.empik.restfulapiproject.model.response.GithubUserInfoResponse;
import com.empik.restfulapiproject.model.response.UserInfoResponse;
import com.empik.restfulapiproject.model.type.ExceptionCodeType;
import com.empik.restfulapiproject.repository.UserInfoRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UserInfoService {

	private final GithubApiClient githubApiClient;
	private final UserInfoRepository userInfoRepository;
	private final UserInfoMapper userInfoMapper;

	public UserInfoResponse getUserInfoByGithubLogin(String login) {
		GithubUserInfoResponse githubUserInfo = githubApiClient.getUserInfoByGithubLogin(login);
		UserInfo userInfo = userInfoRepository.findByLogin(githubUserInfo.getLogin())
				.orElse(userInfoMapper.toEntity(githubUserInfo.getLogin()));
		userInfo.incrementRequestCounter();
		BigDecimal calculations = calculate(githubUserInfo);
		userInfoRepository.save(userInfo);
		log.info("[toResponse] invoked with githubUserInfo=[{}] and calculations=[{}]", githubUserInfo,calculations);
		return userInfoMapper.toResponse(githubUserInfo, calculations);
	}

	private BigDecimal calculate(GithubUserInfoResponse githubUserInfo) {
		log.info("[calculate] invoked with githubUserInfo=[{}]", githubUserInfo);
		Long numberOfFollowers = githubUserInfo.getNumberOfFollowers();
		if (numberOfFollowers == null || numberOfFollowers == 0) {
			log.error("[calculate] Followers number for the user is equal to 0. Calculations can't be proceeded.");
			throw new RestApiException(ExceptionCodeType.ILLEGAL_CALCULATION_ARGUMENT);
		}
		return BigDecimal.valueOf(6.0).divide(BigDecimal.valueOf(numberOfFollowers), 12, RoundingMode.HALF_UP).multiply(
				BigDecimal.valueOf(2.0).add(BigDecimal.valueOf(githubUserInfo.getNumberOfPublicRepositories()))).stripTrailingZeros();
	}
}
