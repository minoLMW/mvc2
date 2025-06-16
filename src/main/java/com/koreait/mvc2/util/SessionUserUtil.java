package com.koreait.mvc2.util;

import com.koreait.mvc2.dto.GoogleDTO;
import com.koreait.mvc2.dto.KakaoDTO;
import com.koreait.mvc2.dto.MemberDTO;

import javax.servlet.http.HttpSession;

public class SessionUserUtil {
    public static String getUserId(HttpSession session) {
        Object user = session.getAttribute("user");
        if (user instanceof MemberDTO) {
            return ((MemberDTO) user).getUserid();
        } else if (user instanceof KakaoDTO) {
            return "kakao_" + ((KakaoDTO) user).getEmail();
        } else if (user instanceof GoogleDTO) {
            return "google_" + ((GoogleDTO) user).getEmail();
        }
        return null;
    }
    /**
     * 세션에서 사용자 정보를 꺼내서 [userId, nickname] 형식으로 반환
     * userId는 provider_이메일 형식 (구글/카카오), 일반 회원은 기존 userid 그대로 사용
     */
    public static String[] getUserIdAndNickname(HttpSession session) {
        Object user = session.getAttribute("user");

        if (user == null) {
            throw new RuntimeException("로그인이 필요합니다.");
        }

        if (user instanceof MemberDTO member) {
            return new String[]{member.getUserid(), member.getName()};
        } else if (user instanceof GoogleDTO google) {
            String userid = "google_" + google.getEmail();
            String nickname = google.getName() != null ? google.getName() : "구글회원";
            return new String[]{userid, nickname};
        } else if (user instanceof KakaoDTO kakao) {
            String userid = "kakao_" + kakao.getEmail();
            String nickname = kakao.getNickname() != null ? kakao.getNickname() : "카카오회원";
            return new String[]{userid, nickname};
        } else {
            throw new RuntimeException("알 수 없는 사용자 유형입니다.");
        }
    }
}
