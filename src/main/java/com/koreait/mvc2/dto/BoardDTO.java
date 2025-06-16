package com.koreait.mvc2.dto;

public class BoardDTO {
    private int board_idx;

    private String userid;

    private String nickname;

    private String title;

    private String content;

    private String regdate;

    private int view_count;

    // 파일 업로드 관련 필드
    private String originalName;      // 클라이언트가 업로드한 실제 파일명
    private String fileName;          // 서버에 저장된 파일명 (UUID 등)
    private String filePath;          // 업로드 경로 (예: uploads/UUID.ext)
    private long fileSize;            // 파일 크기

    public BoardDTO() {}

    public BoardDTO(int board_idx, String userid, String nickname,
                    String title, String content, String regdate,
                    int view_count, String originalName,
                    String fileName, String filePath, long fileSize) {
        this.board_idx   = board_idx;
        this.userid      = userid;
        this.nickname    = nickname;
        this.title       = title;
        this.content     = content;
        this.regdate     = regdate;
        this.view_count  = view_count;
        this.originalName = originalName;
        this.fileName     = fileName;
        this.filePath     = filePath;
        this.fileSize     = fileSize;
    }

    public int getBoard_idx() {
        return board_idx;
    }

    public void setBoard_idx(int board_idx) {
        this.board_idx = board_idx;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
}
