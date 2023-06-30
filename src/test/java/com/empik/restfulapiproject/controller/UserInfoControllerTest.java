package com.empik.restfulapiproject.controller;

import com.empik.restfulapiproject.exception.RestApiException;
import com.empik.restfulapiproject.model.response.UserInfoResponse;
import com.empik.restfulapiproject.model.type.ExceptionCodeType;
import com.empik.restfulapiproject.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserInfoController.class)
class UserInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserInfoService userInfoService;

    @Test
    void getUserInfoByGithubLogin_shouldReturnGithubUserByLoginWhenCorrectLogin() throws Exception {
        //given
        UserInfoResponse userResponse = UserInfoResponse.builder()
                .id(383316L)
                .name("Name")
                .login("test")
                .avatarUrl("https://avatars.githubusercontent.com/u/383316?v=4")
                .createdAt("2010-09-01T10:39:12Z")
                .type("User")
                .calculations(BigDecimal.valueOf(0.875))
                .build();

        //when
        when(userInfoService.getUserInfoByGithubLogin(anyString())).thenReturn(userResponse);

        //then
        mockMvc.perform(get("/users/test"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "id": 383316,
                            "name": "Name",
                            "login": "test",
                            "avatar_url": "https://avatars.githubusercontent.com/u/383316?v=4",
                            "created_at": "2010-09-01T10:39:12Z",
                            "type": "User",
                            "calculations": "0.875"
                        }
                        """));
    }

    @Test
    void getUserInfoByGithubLogin_shouldReturnNotAcceptableWhenGithubUserFollowersEquals0() throws Exception {
        doThrow(new RestApiException(ExceptionCodeType.ILLEGAL_CALCULATION_ARGUMENT))
                .when(userInfoService).getUserInfoByGithubLogin(anyString());

        mockMvc.perform(get("/users/testUser"))
                .andExpect(status().isNotAcceptable())
                .andExpect(content().json("""
                        {
                            "message": "Followers number for the user is equal to 0. Calculations can't be proceeded."
                        }
                        """));
    }

    @Test
    void getUserInfoByGithubLogin_shouldReturnNotFoundWhenGithubUserNotExist() throws Exception {
        doThrow(new RestApiException(ExceptionCodeType.GITHUB_USER_NOT_FOUND)).when(userInfoService)
                .getUserInfoByGithubLogin(anyString());

        mockMvc.perform(get("/users/testUser"))
                .andExpect(status().isNotFound())
                .andExpect(content().json("""
                        {
                            "message": "Github user with given login do not exist"
                        }
                        """));
    }
}
