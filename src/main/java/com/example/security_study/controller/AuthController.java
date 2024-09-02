package com.example.security_study.controller;

import com.example.security_study.dto.auth.LoginRequestDto;
import com.example.security_study.dto.auth.SignupRequestDto;
import com.example.security_study.dto.auth.SignupResponseDto;
import com.example.security_study.dto.token.TokenDto;
import com.example.security_study.response.ResponseTemplate;
import com.example.security_study.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseTemplate<TokenDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return new ResponseTemplate<>(HttpStatus.OK, "로그인 성공", authService.login(loginRequestDto));
    }

    @PostMapping("/signup")
    public ResponseTemplate<SignupResponseDto> signup(@RequestBody SignupRequestDto signupRequestDto) {
        return new ResponseTemplate<>(HttpStatus.CREATED, "회원가입 성공", authService.signup(signupRequestDto));
    }

    @PostMapping("/reissue")
    public ResponseTemplate<TokenDto> reissue(@RequestBody TokenDto tokenDto) {
        return new ResponseTemplate<>(HttpStatus.OK, "액세스 토큰 재발급 성공", authService.reissue(tokenDto));
    }
}
