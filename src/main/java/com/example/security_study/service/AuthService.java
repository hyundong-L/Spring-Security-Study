package com.example.security_study.service;

import com.example.security_study.domain.Role;
import com.example.security_study.domain.UserInfo;
import com.example.security_study.dto.ProfileUpdateRequestDto;
import com.example.security_study.image.service.ImageService;
import com.example.security_study.repository.UserRepository;
import com.example.security_study.security.jwt.dto.TokenDto;
import com.example.security_study.security.jwt.util.TokenProvider;
import com.example.security_study.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final ImageService imageService;

    @Transactional
    public TokenDto signup(ProfileUpdateRequestDto profileUpdateRequestDto) throws IOException {
        Long userId = SecurityUtil.getCurrentUserId();
        UserInfo userInfo = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("존재하지 않는 사용자"));

        /*
        SecurityConfig에서 hasAuthority("ROLE_GUEST")를 작성했는데, 여기 또 작성한 이유
            토큰 재발급 후, 이전 토큰으로 요청을 보낸다면 토큰은 ROLE_GUEST, DB에는 ROLE_USER가 된다. 따라서 이런 비정상적인 접근을 방지하기 위해 작성한다.
         */
        if(!userInfo.getRole().equals(Role.ROLE_GUEST) || userInfo.getNickname() != null || userInfo.getImageUrl() != null){
            throw new RuntimeException("이미 회원가입이 된 사용자");
        }

        String newImageUrl = imageService.uploadImage(profileUpdateRequestDto.getImage());
        log.info("New Profile image uploaded: {}", newImageUrl);

        userInfo.updateProfileInfo(profileUpdateRequestDto.getNickname(), newImageUrl);
        userInfo.updateRole();

        //Role을 업데이트 후, 헤더의 토큰에 담긴 권한도 수정이 필요하므로, Access token 재발급 진행
        return tokenProvider.generateAccessTokenByRefreshToken(userId, Role.ROLE_USER, userInfo.getRefreshToken());
    }
}
