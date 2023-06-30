package com.empik.restfulapiproject.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empik.restfulapiproject.model.response.UserInfoResponse;
import com.empik.restfulapiproject.service.UserInfoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping(path = "/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserInfoResponse getUserInfoByGithubLogin(@PathVariable String login) {
        return userInfoService.getUserInfoByGithubLogin(login);
    }
}
