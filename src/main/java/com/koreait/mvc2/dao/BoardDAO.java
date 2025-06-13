package com.koreait.mvc2.dao;

import com.koreait.mvc2.dto.BoardDTO;
import com.koreait.mvc2.dto.MemberDTO;
import com.koreait.mvc2.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    public static List<BoardDTO> create() {
        List<BoardDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM board ORDER BY board_idx DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                BoardDTO dto = new BoardDTO();
                dto.setBoard_idx(rs.getInt("board_idx"));
                dto.setUserid(rs.getString("userid"));
                dto.setNickname(rs.getString("nickname"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setCreated_at(rs.getString("created_at"));
                dto.setView_count(rs.getInt("view_count"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }
    // 게시글 등록
    public static void create(BoardDTO dto) {
        String sql = "INSERT INTO board (title, content, nickname) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dto.getTitle());
            ps.setString(2, dto.getContent());
            ps.setString(3, dto.getNickname());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 게시글 상세보기
    public static BoardDTO detail(int board_idx) {
        BoardDTO dto = null;
        String sql = "SELECT * FROM board WHERE board_idx = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, board_idx);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dto = new BoardDTO();
                    dto.setBoard_idx(rs.getInt("board_idx"));
                    dto.setNickname(rs.getString("nickname"));
                    dto.setUserid(rs.getString("userid"));
                    dto.setTitle(rs.getString("title"));
                    dto.setContent(rs.getString("content"));
                    dto.setCreated_at(rs.getString("created_at"));
                    dto.setView_count(rs.getInt("view_count"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }
    // 조회수 증가
    public static void incrementViews(int board_idx) {
        String sql = "UPDATE board SET view_count = view_count + 1 WHERE board_idx = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, board_idx);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 게시글 수정
    public static void edit(BoardDTO dto) {
        String sql = "UPDATE board SET title = ?, content = ? WHERE board_idx = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dto.getTitle());
            ps.setString(2, dto.getContent());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 게시글 삭제
    public static void remove(int board_idx) {
        String sql = "DELETE FROM board WHERE board_idx = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, board_idx);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
}
