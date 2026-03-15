package com.link_organizer.oauth.auth.controller;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

//    @GetMapping("/me")
//    public Map<String, Object> me(@AuthenticationPrincipal OAuth2User principal) {
//        if (principal == null) {
//            return Map.of("authenticated", false);
//        }
//        Map<String, Object> attrs = principal.getAttributes();
//        return Map.of(
//            "authenticated", true,
//            "email", attrs.get("email"),
//            "name", attrs.get("name"),
//            "picture", attrs.get("picture")
//        );
//    }
}