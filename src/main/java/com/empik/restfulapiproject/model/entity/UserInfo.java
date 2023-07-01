package com.empik.restfulapiproject.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_info")
public class UserInfo {

    @Id
    @Column(name = "login")
    private String login;

    @Column(name = "request_count")
    private Long requestCount;

    public void incrementRequestCounter() {
        this.requestCount++;
    }
}
