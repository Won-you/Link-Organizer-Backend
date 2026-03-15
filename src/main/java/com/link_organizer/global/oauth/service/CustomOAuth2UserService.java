//package com.link_organizer.global.oauth.service;
//
//import com.link_organizer.common.enums.AccountRole;
//import com.link_organizer.domain.accounts.entity.Accounts;
//import com.link_organizer.domain.accounts.repository.AccountRepository;
//import java.util.Collections;
//import java.util.Map;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
////import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
////import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
////import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
////import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
////import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
////import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//// @Service  // ID Token 방식으로 전환으로 인해 비활성화
//@RequiredArgsConstructor
//public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
//
//    private final AccountRepository accountRepository;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
//        OAuth2User oAuth2User = delegate.loadUser(userRequest);
//
//        String registrationId = userRequest.getClientRegistration().getRegistrationId();
//        String userNameAttributeName = userRequest.getClientRegistration()
//                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
//
//        Map<String, Object> attributes = oAuth2User.getAttributes();
//
//        // Google
//        String email = (String) attributes.get("email");
//        String name = (String) attributes.get("name");
//        String picture = (String) attributes.get("picture");
//        String providerId = (String) attributes.get("sub");
//
//        Accounts account = saveOrUpdate(email, name, picture, registrationId, providerId);
//
//        return new DefaultOAuth2User(
//                Collections.singleton(new SimpleGrantedAuthority(account.getRole().name())),
//                attributes,
//                userNameAttributeName);
//    }
//
//    /// 기존 유저: 구글 프로필(이름, 사진)이 변경되었을 수 있으므로 최신 정보로 업데이트
//    private Accounts saveOrUpdate(String email, String name, String picture, String provider, String providerId) {
//        Accounts account = accountRepository.findByEmail(email)
//                .map(entity -> entity.update(name, picture))
//                .orElse(Accounts.builder()
//                        .email(email)
//                        .nickname(name)
//                        .profileImageUrl(picture)
//                        .provider(provider)
//                        .providerId(providerId)
//                        .role(AccountRole.ROLE_USER)
//                        .build());
//
//        return accountRepository.save(account);
//    }
//}
