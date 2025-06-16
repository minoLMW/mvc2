package com.koreait.mvc2.dao;

import com.koreait.mvc2.dto.GoogleDTO;
import com.koreait.mvc2.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GoogleDAO {

    // email 기준으로 중복 확인
    public boolean isExist(GoogleDTO dto) {
        String sql = "SELECT COUNT(*) FROM google_user WHERE email = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dto.getEmail());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 사용자 등록
    public void insert(GoogleDTO dto) {
        String sql = "INSERT INTO google_user (email, name, picture) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dto.getEmail());
            ps.setString(2, dto.getName());
            ps.setString(3, dto.getPicture());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
