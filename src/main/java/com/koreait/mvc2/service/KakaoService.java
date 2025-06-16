package com.koreait.mvc2.service;

import com.koreait.mvc2.dto.KakaoDTO;

import javax.servlet.http.HttpSession;

public interface KakaoService {
    String getAccessToken(String code);
    KakaoDTO getUserInfo(String accessToken);
    void registerOrLogin(KakaoDTO userInfo, HttpSession session);
}
