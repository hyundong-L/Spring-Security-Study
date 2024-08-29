package com.example.security_study.controller;

import com.example.security_study.dto.ProfileUpdateRequestDto;
import com.example.security_study.response.ResponseTemplate;
import com.example.security_study.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UserInfoController {
    private final UserInfoService userInfoService;

    @PutMapping("/user/info")
    public ResponseTemplate<?> updateUserInfoProfile(@RequestPart ProfileUpdateRequestDto profileUpdateRequestDto) throws IOException {
        userInfoService.updateUserInfoProfile(profileUpdateRequestDto);
        return new ResponseTemplate<>(HttpStatus.OK, "프로필 수정 성공");
    }
}
