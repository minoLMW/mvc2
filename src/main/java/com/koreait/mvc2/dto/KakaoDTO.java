package com.koreait.mvc2.dto;

public class KakaoDTO {
    private String kakaoId;  // DB의 kakao_id 컬럼 대응
    private String email;
    private String nickname;

    public KakaoDTO() {}

    public KakaoDTO(String kakaoId, String email, String nickname) {
        this.kakaoId = kakaoId;
        this.email = email;
        this.nickname = nickname;
    }

    public String getKakaoId() { return kakaoId; }
    public void setKakaoId(String kakaoId) { this.kakaoId = kakaoId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getUserid() {
        return "kakao_" + email;
    }
}
