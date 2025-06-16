package com.koreait.mvc2.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;

public class GoogleAuthService {

    private static final String CLIENT_ID = "1068789889786-drr2n0fge0ctrn6p0pgetdaguocq0e0p.apps.googleusercontent.com"; // ← 여기에 네 클라이언트 ID
    private static final String CLIENT_SECRET = "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"; // ← 여기에 네 시크릿 키
    private static final String REDIRECT_URI = "http://localhost:8081/mvc2_war/oauth2callback"; // 실제 콜백 URL

    // Access Token으로 사용자 정보 조회
    public Userinfoplus getUserInfo(String code) throws Exception {

        // 1. Access Token 요청
        GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(),
                "https://oauth2.googleapis.com/token",
                CLIENT_ID,
                CLIENT_SECRET,
                code,
                REDIRECT_URI
        ).execute();

        // 2. Credential 생성
        GoogleCredential credential = new GoogleCredential().setAccessToken(tokenResponse.getAccessToken());

        // 3. 사용자 정보 요청
        Oauth2 oauth2 = new Oauth2.Builder(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(),
                credential
        ).setApplicationName("GoogleLoginExample").build();

        return oauth2.userinfo().get().execute();  // 이메일, 이름, 사진 등 포함
    }
}