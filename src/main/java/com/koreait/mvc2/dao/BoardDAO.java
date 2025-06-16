package com.koreait.mvc2.dao;

import com.koreait.mvc2.dto.BoardDTO;
import com.koreait.mvc2.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    // 게시글 목록 조회
    public List<BoardDTO> list() {
        List<BoardDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM board ORDER BY board_idx DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                BoardDTO dto = new BoardDTO();
                dto.setBoard_idx(rs.getInt("board_idx"));
                dto.setUserid(rs.getString("userid"));
                dto.setNickname(rs.getString("nickname"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setRegdate(rs.getString("regdate"));
                dto.setView_count(rs.getInt("view_count"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 게시글 등록 (트랜잭션: board + file 테이블)
    public boolean create(BoardDTO dto) {
        String boardSql = "INSERT INTO board (title, content, userid, nickname, regdate) VALUES (?, ?, ?, ?, NOW())";
        String fileSql  = "INSERT INTO file (original_name, stored_name, upload_path, board_idx) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement psBoard = null;
        PreparedStatement psFile  = null;
        ResultSet rsKeys = null;

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            // 1) board 테이블에 게시글 삽입
            psBoard = conn.prepareStatement(boardSql, Statement.RETURN_GENERATED_KEYS);
            psBoard.setString(1, dto.getTitle());
            psBoard.setString(2, dto.getContent());
            psBoard.setString(3, dto.getUserid());
            psBoard.setString(4, dto.getNickname());
            int count = psBoard.executeUpdate();
            if (count == 0) throw new SQLException("게시글 삽입 실패");

            // 2) 생성된 board_idx 키 조회
            rsKeys = psBoard.getGeneratedKeys();
            if (!rsKeys.next()) throw new SQLException("board_idx 키 가져오기 실패");
            int boardIdx = rsKeys.getInt(1);

            // 3) 파일 정보가 있으면 file 테이블에 삽입
            if (dto.getFileName() != null && !dto.getFileName().isEmpty()) {
                psFile = conn.prepareStatement(fileSql);
                psFile.setString(1, dto.getOriginalName());
                psFile.setString(2, dto.getFileName());
                psFile.setString(3, dto.getFilePath());
                psFile.setInt   (4, boardIdx);
                psFile.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ignore) {}
            }
            return false;
        } finally {
            try { if (rsKeys  != null) rsKeys.close(); }  catch (SQLException ignore) {}
            try { if (psFile  != null) psFile.close(); }  catch (SQLException ignore) {}
            try { if (psBoard != null) psBoard.close(); } catch (SQLException ignore) {}
            try { if (conn    != null) conn.close(); }    catch (SQLException ignore) {}
        }
    }

    // 게시글 상세보기
    public BoardDTO detail(int board_idx) {
        BoardDTO dto = null;
        String boardSql = "SELECT * FROM board WHERE board_idx = ?";
        String fileSql  = "SELECT original_name, stored_name, upload_path, size FROM file WHERE board_idx = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement psBoard = conn.prepareStatement(boardSql)) {

            psBoard.setInt(1, board_idx);
            try (ResultSet rs = psBoard.executeQuery()) {
                if (rs.next()) {
                    dto = new BoardDTO();
                    dto.setBoard_idx(rs.getInt("board_idx"));
                    dto.setUserid(rs.getString("userid"));
                    dto.setNickname(rs.getString("nickname"));
                    dto.setTitle(rs.getString("title"));
                    dto.setContent(rs.getString("content"));
                    dto.setRegdate(rs.getString("regdate"));
                    dto.setView_count(rs.getInt("view_count"));

                    // 파일 정보 조회
                    try (PreparedStatement psFile = conn.prepareStatement(fileSql)) {
                        psFile.setInt(1, board_idx);
                        try (ResultSet rsFile = psFile.executeQuery()) {
                            if (rsFile.next()) {
                                dto.setOriginalName(rsFile.getString("original_name"));
                                dto.setFileName(rsFile.getString("stored_name"));
                                dto.setFilePath(rsFile.getString("upload_path"));
                                dto.setFileSize(rsFile.getLong("size"));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    // 조회수 증가
    public void incrementViews(int board_idx) {
        String sql = "UPDATE board SET view_count = view_count + 1 WHERE board_idx = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, board_idx);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 게시글 수정 (파일 변경 미지원)
    public boolean edit(BoardDTO dto) {
        String sql = "UPDATE board SET title = ?, content = ? WHERE board_idx = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dto.getTitle());
            ps.setString(2, dto.getContent());
            ps.setInt(3, dto.getBoard_idx());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 게시글 삭제
    public boolean remove(int board_idx) {
        String sql = "DELETE FROM board WHERE board_idx = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, board_idx);
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
