package com.example.security_study.repository;

import com.example.security_study.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByLoginId(String loginId);
    Optional<UserInfo> findByRefreshToken(String refreshToken);
}
