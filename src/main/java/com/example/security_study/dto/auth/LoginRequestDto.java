package com.example.security_study.dto.auth;

import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String loginId;
    private String password;
}
