package com.example.security_study.domain;

import com.example.security_study.domain.enums.Authority;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_info_id")
    private Long id;

    @Column(name = "login_id", unique = true)
    private String loginId;

    @Column
    private String password;

    @Column
    private String username;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Builder
    public UserInfo(String loginId, String password, String username, Authority authority) {
        this.loginId = loginId;
        this.password = password;
        this.username = username;
        this.authority = authority;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
