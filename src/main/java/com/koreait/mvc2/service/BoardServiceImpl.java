package com.koreait.mvc2.service;

import com.koreait.mvc2.dao.BoardDAO;
import com.koreait.mvc2.dto.BoardDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BoardServiceImpl implements BoardService {
    private BoardDAO dao = new BoardDAO();

    @Override
    public void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            BoardDTO dto = new BoardDTO();
        dto.setUserid(req.getParameter("userid"));
        dto.setNickname(req.getParameter("nickname"));
        dto.setTitle(req.getParameter("title"));
        dto.setContent(req.getParameter("content"));

        boolean success = dao.create(dto); // 먼저 실행

        req.setAttribute("success", success);
        req.getRequestDispatcher("/WEB-INF/views/result.jsp").forward(req, resp);
        // boolean success = dao.create(dto);
        // success 변수를 선언하기 전에 먼저 req.setAttribute("success", success) 이건 컴파일 오류 발생
    }

    @Override
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void incrementViews(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoardDTO sessionDto = (BoardDTO) req.getSession().getAttribute("boardUser");
        BoardDTO dto = new BoardDTO();
        dto.setUserid(sessionDto.getUserid());
        dto.setNickname(req.getParameter("nickname"));
        dto.setTitle(req.getParameter("title"));
        dto.setContent(req.getParameter("content"));
        boolean isModify = dao.update(dto); // updateBoard -> update, 실제 BoardDAO안에서는 메서드명이 update()
        if(isModify) {
            req.getSession().setAttribute("user", dto);
        }
        req.setAttribute("isModify", isModify);
        req.getRequestDispatcher("/WEB-INF/views/mypage.jsp").forward(req, resp);
    }

    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
