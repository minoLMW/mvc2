package com.koreait.mvc2.dao;

import com.koreait.mvc2.dto.BoardCommentDTO;
import com.koreait.mvc2.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardCommentDAO {

    // 🔹 댓글 목록 조회
    public List<BoardCommentDTO> getCommentsByBoardIdx(int board_idx) {
        List<BoardCommentDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM board_comment WHERE board_idx = ? ORDER BY comment_idx ASC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, board_idx);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BoardCommentDTO dto = new BoardCommentDTO();
                dto.setComment_idx(rs.getInt("comment_idx"));
                dto.setBoard_idx(rs.getInt("board_idx"));
                dto.setUser_id(rs.getString("user_id"));
                dto.setContent(rs.getString("content"));
                dto.setCreated_at(rs.getString("created_at"));
                list.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // 🔹 댓글 등록
    public boolean insertComment(BoardCommentDTO dto) {
        String sql = "INSERT INTO board_comment (board_idx, user_id, content, created_at) VALUES (?, ?, ?, NOW())";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, dto.getBoard_idx());
            ps.setString(2, dto.getUser_id());
            ps.setString(3, dto.getContent());

            int result = ps.executeUpdate();
            return result == 1;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}