package com.koreait.mvc2.dao;

import com.koreait.mvc2.dto.KakaoDTO;
import com.koreait.mvc2.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class KakaoDAO {

    // kakao_id를 기준으로 중복 체크
    public boolean isExist(KakaoDTO userInfo) {
        boolean result = false;
        String sql = "SELECT COUNT(*) FROM kakao_user WHERE kakao_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userInfo.getKakaoId());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = rs.getInt(1) > 0;
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // 회원 저장
    public void insert(KakaoDTO userInfo) {
        String sql = "INSERT INTO kakao_user (kakao_id, email, nickname) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, userInfo.getKakaoId());
            ps.setString(2, userInfo.getEmail());
            ps.setString(3, userInfo.getNickname());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
