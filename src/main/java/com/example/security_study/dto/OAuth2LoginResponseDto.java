package com.example.security_study.dto;

import com.example.security_study.security.jwt.dto.TokenDto;
import lombok.Setter;

@Setter
public class OAuth2LoginResponseDto {
    private TokenDto token;
    private Boolean isNewUser;
    private String message;
}
