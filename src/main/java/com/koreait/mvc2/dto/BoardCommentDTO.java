package com.koreait.mvc2.dto;

public class BoardCommentDTO {
    private int comment_idx;       // 댓글 고유 번호
    private int board_idx;         // 게시글 번호 (외래키)
    private String user_id;        // 댓글 작성자 ID
    private String content;        // 댓글 내용
    private String created_at;     // 작성 시간 (문자열로 받음)

    // Getter & Setter
    public int getComment_idx() {
        return comment_idx;
    }

    public void setComment_idx(int comment_idx) {
        this.comment_idx = comment_idx;
    }

    public int getBoard_idx() {
        return board_idx;
    }

    public void setBoard_idx(int board_idx) {
        this.board_idx = board_idx;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}