package com.link_organizer.domain.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder

public class JwtResponse {
  // 실제 API 요청 시 HTTP Header(Authorization)에 담아 보낼 단기 토큰
  private final String accessToken;

  private final String refreshToken;

  @Builder.Default
  private final String tokenType = "Bearer";

  // 만약 토큰 만료 시간까지 알려주고 싶다면 아래 필드를 추가하기도 합니다.
  // private final Long expiresIn;
}
