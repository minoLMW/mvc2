package com.koreait.mvc2.service;

import com.google.gson.Gson;
import com.koreait.mvc2.dto.KakaoDTO;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class KakaoServiceImpl implements KakaoService {

    private static Map<String, KakaoDTO> kakaoUserDB = new HashMap<>();

    private static final String CLIENT_ID = "7d182da6678fff468dfa3be56fcca737";
    private static final String REDIRECT_URI = "http://localhost:8081/mvc2_war/kakao.member";
    private static final String TOKEN_URL = "https://kauth.kakao.com/oauth/token";
    private static final String USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";

    @Override
    public String getAccessToken(String code) {
        String accessToken = "";
        try {
            URL url = new URL(TOKEN_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            String params = "grant_type=authorization_code"
                    + "&client_id=" + CLIENT_ID
                    + "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, "UTF-8")
                    + "&code=" + code;

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()))) {
                bw.write(params);
                bw.flush();
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    conn.getResponseCode() == 200 ? conn.getInputStream() : conn.getErrorStream()
            ))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                Map<String, Object> map = new Gson().fromJson(sb.toString(), Map.class);
                accessToken = (String) map.get("access_token");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }

    @Override
    public KakaoDTO getUserInfo(String accessToken) {
        KakaoDTO dto = new KakaoDTO();
        try {
            URL url = new URL(USER_INFO_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    conn.getResponseCode() == 200 ? conn.getInputStream() : conn.getErrorStream()
            ))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                Map<String, Object> map = new Gson().fromJson(sb.toString(), Map.class);
                dto.setKakaoId(String.valueOf(map.get("id")));  // 여기 수정!

                Map<String, Object> kakaoAccount = (Map<String, Object>) map.get("kakao_account");
                if (kakaoAccount != null) {
                    dto.setEmail((String) kakaoAccount.get("email"));
                    Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
                    if (profile != null) {
                        dto.setNickname((String) profile.get("nickname"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public void registerOrLogin(KakaoDTO userInfo, HttpSession session) {
        if (!kakaoUserDB.containsKey(userInfo.getKakaoId())) {  // 수정!
            kakaoUserDB.put(userInfo.getKakaoId(), userInfo);    // 수정!
        }
        session.setAttribute("user", userInfo);
    }
}
