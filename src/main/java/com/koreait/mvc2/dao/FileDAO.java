package com.koreait.mvc2.dao;

import com.koreait.mvc2.dto.FileDTO;
import com.koreait.mvc2.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileDAO {
    public int insertFile(FileDTO file) throws Exception {
        String sql = "INSERT INTO file (board_idx, original_name, stored_name, content_type, size, upload_path) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, file.getBoardIdx());
            ps.setString(2, file.getOriginalName());
            ps.setString(3, file.getStoredName());
            ps.setString(4, file.getContentType());
            ps.setLong(5, file.getSize());
            ps.setString(6, file.getUploadPath());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) file.setFileIdx(rs.getInt(1));
            }
        }
        return file.getFileIdx();
    }

    public FileDTO selectFile(int fileIdx) throws Exception {
        String sql = "SELECT * FROM file WHERE file_idx = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, fileIdx);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    FileDTO f = new FileDTO();
                    // 필드 매핑
                    return f;
                }
            }
        }
        return null;
    }

    public List<FileDTO> selectFilesByBoardIdx(int boardIdx) throws Exception {
        String sql = "SELECT * FROM file WHERE board_idx = ?";
        List<FileDTO> list = new ArrayList<>();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, boardIdx);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    FileDTO f = new FileDTO();
                    // 필드 매핑
                    list.add(f);
                }
            }
        }
        return list;
    }

    public int deleteFile(int fileIdx) throws Exception {
        String sql = "DELETE FROM file WHERE file_idx = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, fileIdx);
            return ps.executeUpdate();
        }
    }
}
