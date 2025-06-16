package com.koreait.mvc2.service;

import com.koreait.mvc2.dto.KakaoDTO;
import javax.servlet.http.HttpSession;

public interface KakaoService {
    /**
     * 로그인 시작을 위한 카카오 인증 URL 생성
     * @return 카카오 OAuth 인가 페이지 URL
     */
    String getAuthUrl();

    /**
     * 인가 코드를 이용해 액세스 토큰 발급
     * @param code 카카오에서 받은 인가 코드
     * @return 액세스 토큰
     */
    String getAccessToken(String code);

    /**
     * 액세스 토큰으로 사용자 정보 조회
     * @param accessToken 발급받은 토큰
     * @return 사용자 정보 DTO
     */
    KakaoDTO getUserInfo(String accessToken);

    /**
     * 받아온 사용자 정보를 세션에 저장하거나 회원 가입/로그인 처리
     * @param userInfo 카카오 사용자 정보
     * @param session HttpSession 객체
     */
    void registerOrLogin(KakaoDTO userInfo, HttpSession session);
}
