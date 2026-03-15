//package com.link_organizer.global.oauth.handler;
//
//import com.link_organizer.domain.accounts.repository.AccountRepository;
//import com.link_organizer.global.jwt.JwtTokenProvider;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Map;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
////import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//import org.springframework.web.util.UriComponentsBuilder;
//
//// @Component  // ID Token 방식으로 전환으로 인해 비활성화
//@RequiredArgsConstructor
//public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//
////    private final JwtTokenProvider jwtTokenProvider;
////    private final AccountRepository accountRepository;
////
////    @org.springframework.beans.factory.annotation.Value("${spring.oauth2.redirect-uri}")
////    private String redirectUri;
////
////    @Override
////    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
////            Authentication authentication) throws IOException {
////        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
////        Map<String, Object> attributes = oAuth2User.getAttributes();
////        String email = (String) attributes.get("email");
////
////        String accessToken = jwtTokenProvider.createAccessToken(email);
////        String refreshToken = jwtTokenProvider.createRefreshToken(email);
////
////        // Refresh Token DB 저장/갱신
////        accountRepository.findByEmail(email).ifPresent(account -> {
////            account.setRefreshToken(refreshToken);
////            accountRepository.save(account);
////        });
////
////        // 프론트엔드(또는 앱)로 리다이렉트 (쿼리 파라미터에 토큰 포함)
////        String targetUrl = UriComponentsBuilder.fromUriString(redirectUri)
////                .queryParam("accessToken", accessToken)
////                .queryParam("refreshToken", refreshToken)
////                .build().toUriString();
////
////        getRedirectStrategy().sendRedirect(request, response, targetUrl);
////    }
//}
