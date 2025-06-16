package com.koreait.mvc2.dto;

public class BoardDTO {
    private int board_idx;
    private String userid;
    private String nickname;
    private String title;
    private String content;
    private String regdate;
    private int view_count;

    public BoardDTO() {}

    public BoardDTO(int board_idx, String userid, String nickname, String title, String content, String regdate, int view_count) {
        this.board_idx = board_idx;
        this.userid = userid;
        this.nickname = nickname;
        this.title = title;
        this.content = content;
        this.regdate = regdate;
        this.view_count = view_count;
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
}
