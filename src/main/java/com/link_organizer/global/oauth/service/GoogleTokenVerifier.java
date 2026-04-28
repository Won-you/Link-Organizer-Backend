package com.link_organizer.global.oauth.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GoogleTokenVerifier {

    private final GoogleIdTokenVerifier verifier;

    public GoogleTokenVerifier(@Value("${google.client-id}") String clientId) {
//        log.info("google : {}",clientId);
        this.verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(clientId))
                .build();
    }

    public GoogleIdToken.Payload verify(String idToken) {
        try {
            GoogleIdToken googleIdToken = verifier.verify(idToken);
            if (googleIdToken == null) {
                throw new IllegalArgumentException("유효하지 않은 Google ID Token입니다.");
            }
            return googleIdToken.getPayload();
        } catch (Exception e) {
            log.error("Google ID Token 검증 실패: {} | 예외 타입: {}",
                e.getMessage(), e.getClass().getName(), e);
            throw new IllegalArgumentException("Google ID Token 검증에 실패했습니다.");
        }
    }
}
