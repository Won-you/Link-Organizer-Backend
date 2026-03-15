package com.link_organizer.domain.accounts.dto;

import com.link_organizer.common.enums.AccountRole;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@RequiredArgsConstructor
public class AccountLoginResponseDto {

  Long accountId;

  String nickname;

  String profileImageUrl;

  String provider;

  AccountRole role;

  String accessToken;

  String refreshToken;
}
