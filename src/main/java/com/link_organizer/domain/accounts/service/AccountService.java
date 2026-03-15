package com.link_organizer.domain.accounts.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.link_organizer.common.enums.AccountRole;
import com.link_organizer.domain.accounts.dto.AccountLoginResponseDto;
import com.link_organizer.domain.accounts.dto.JwtResponse;
import com.link_organizer.domain.accounts.entity.Accounts;
import com.link_organizer.domain.accounts.repository.AccountRepository;
import com.link_organizer.global.jwt.JwtTokenProvider;
import com.link_organizer.global.oauth.service.GoogleTokenVerifier;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final GoogleTokenVerifier googleTokenVerifier;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public AccountLoginResponseDto googleLogin(String idToken) {
        // 1. Google ID Token 검증 및 유저 정보 추출
        GoogleIdToken.Payload payload = googleTokenVerifier.verify(idToken);

        String email = payload.getEmail();
        String name = (String) payload.get("name");
        String picture = (String) payload.get("picture");
        String providerId = payload.getSubject();

        // 2. JWT 발급
        String accessToken = jwtTokenProvider.createAccessToken(email);
        String refreshToken = jwtTokenProvider.createRefreshToken(email);

        // 3. DB에 저장 또는 업데이트 (refreshToken 포함)
        Accounts account = saveOrUpdate(email, name, picture, "google", providerId, refreshToken);

        return AccountLoginResponseDto.builder()
                .accountId(account.getAccountId())
                .nickname(account.getNickname())
                .profileImageUrl(account.getProfileImageUrl())
                .provider(account.getProvider())
                .role(account.getRole())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Transactional
    public JwtResponse reissue(String refreshToken) {
        // 1. refreshToken 검증
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new IllegalArgumentException("유효하지 않은 Refresh Token입니다.");
        }

        // 2. refreshToken으로 email 추출 후 유저 조회
        String email = jwtTokenProvider.getEmailFromToken(refreshToken);
        Accounts account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        // 3. DB의 refreshToken과 일치하는지 확인
        if (!refreshToken.equals(account.getRefreshToken())) {
            throw new IllegalArgumentException("Refresh Token이 일치하지 않습니다.");
        }

        // 4. 새 토큰 발급 후 DB 저장
        String newAccessToken = jwtTokenProvider.createAccessToken(email);
        String newRefreshToken = jwtTokenProvider.createRefreshToken(email);
        account.setRefreshToken(newRefreshToken);

        return JwtResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .build();
    }


    private Accounts saveOrUpdate(String email, String name, String picture, String provider, String providerId, String refreshToken) {
        return accountRepository.findByEmail(email)
                .map(entity -> { /// 사용자가 존재하는 경우
                    entity.update(name, picture);
                    entity.setRefreshToken(refreshToken);
                    return accountRepository.save(entity);
                })
                .orElseGet(() -> accountRepository.save(Accounts.builder() ///  등록되지 않는 사용자의 경우
                        .email(email)
                        .nickname(name)
                        .profileImageUrl(picture)
                        .provider(provider)
                        .providerId(providerId)
                        .role(AccountRole.ROLE_USER)
                        .refreshToken(refreshToken)
                        .build()));
    }


}
