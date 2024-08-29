package com.example.security_study.service;

import com.example.security_study.domain.UserInfo;
import com.example.security_study.dto.ProfileUpdateRequestDto;
import com.example.security_study.image.service.ImageService;
import com.example.security_study.repository.UserRepository;
import com.example.security_study.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserInfoService {
    private final UserRepository userRepository;
    private final ImageService imageService;

    @Transactional
    public void updateUserInfoProfile(ProfileUpdateRequestDto profileUpdateRequestDto) throws IOException {
        Long userId = SecurityUtil.getCurrentUserId();
        UserInfo userInfo = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다."));

        if (userInfo.getImageUrl() != null) {
            log.info("Deleting existing image: {}", userInfo.getImageUrl());
            imageService.deleteImage(userInfo.getImageUrl());
        }

        String newImageUrl = imageService.uploadImage(profileUpdateRequestDto.getImage());
        log.info("New image uploaded: {}", newImageUrl);

        userInfo.updateProfileInfo(profileUpdateRequestDto.getNickname(), newImageUrl);
    }
}
