package com.koreait.mvc2.dao;

import com.koreait.mvc2.dto.BoardDTO;
import com.koreait.mvc2.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    // 게시글 목록 조회
    public List<BoardDTO> list() {
        List<BoardDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM board ORDER BY board_idx DESC";
        System.out.println("게시글 목록 조회 시작");
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            System.out.println("SQL 실행: " + sql);
            ResultSet rs = pstmt.executeQuery();
            
            // 테이블 구조 확인
            System.out.println("테이블 구조 확인:");
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                System.out.println("컬럼 " + i + ": " + metaData.getColumnName(i) + " (" + metaData.getColumnTypeName(i) + ")");
            }
            
            while (rs.next()) {
                System.out.println("게시글 데이터 발견: board_idx=" + rs.getInt("board_idx"));
                BoardDTO dto = new BoardDTO();
                dto.setBoard_idx(rs.getInt("board_idx"));
                dto.setUserid(rs.getString("userid"));
                dto.setNickname(rs.getString("nickname"));
                dto.setTitle(rs.getString("title"));
                dto.setContent(rs.getString("content"));
                dto.setRegdate(rs.getString("regdate"));
                dto.setView_count(rs.getInt("view_count"));
                list.add(dto);
                System.out.println("게시글 추가: " + dto.getTitle());
            }
            System.out.println("총 " + list.size() + "개의 게시글을 조회했습니다.");
        } catch (Exception e) {
            System.out.println("게시글 목록 조회 중 오류 발생:");
            e.printStackTrace();
        }
        return list;
    }
    // 게시글 등록
    public boolean create(BoardDTO dto) {
        String sql = "INSERT INTO board (title, content, userid, nickname, regdate) VALUES (?, ?, ?, ?, NOW())";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dto.getTitle());
            ps.setString(2, dto.getContent());
            ps.setString(3, dto.getUserid());
            ps.setString(4, dto.getNickname());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // 게시글 상세보기
    public BoardDTO detail(int board_idx) {
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
                    dto.setRegdate(rs.getString("regdate"));
                    dto.setView_count(rs.getInt("view_count"));
                    
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
    // 게시글 수정
    public boolean edit(BoardDTO dto) {
        String sql = "UPDATE board SET title = ?, content = ? WHERE board_idx = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dto.getTitle());
            ps.setString(2, dto.getContent());
            ps.setInt(3, dto.getBoard_idx());
            int result = ps.executeUpdate();
            return result > 0;
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
            int result = ps.executeUpdate();
            return result == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public BoardDTO selectByIdx(int board_idx) {
        BoardDTO dto = null;
        String sql = "SELECT * FROM board WHERE board_idx = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, board_idx);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dto = new BoardDTO();
                    dto.setBoard_idx(rs.getInt("board_idx"));
                    dto.setTitle(rs.getString("title"));
                    dto.setContent(rs.getString("content"));
                    dto.setUserid(rs.getString("userid"));
                    dto.setNickname(rs.getString("nickname"));
                    dto.setRegdate(rs.getString("regdate"));
                    dto.setView_count(rs.getInt("view_count"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    public int update(BoardDTO dto) {
        int result = 0;
        String sql = "UPDATE board SET title = ?, content = ? WHERE board_idx = ?";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dto.getTitle());
            ps.setString(2, dto.getContent());
            ps.setInt(3, dto.getBoard_idx());
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int insert(BoardDTO dto) {
        int result = 0;
        String sql = "INSERT INTO board (title, content, userid, nickname, regdate) VALUES (?, ?, ?, ?, NOW())";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dto.getTitle());
            ps.setString(2, dto.getContent());
            ps.setString(3, dto.getUserid());
            ps.setString(4, dto.getNickname());
            result = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

