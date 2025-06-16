package com.koreait.mvc2.controller;

import com.koreait.mvc2.service.BoardService;
import com.koreait.mvc2.service.BoardServiceImpl;
import com.koreait.mvc2.util.SessionUserUtil;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BoardService service = new BoardServiceImpl();

    public BoardController() {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doAction(req, resp);
    }

    protected void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String uri = req.getRequestURI();
        String context = req.getContextPath();
        String command = uri.substring(context.length());

        switch (command) {
            case "/create.board":
                req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, resp);
                break;
            case "/createForm.board":
                service.create(req, resp);
                break;
            case "/list.board":
                service.list(req, resp);
                String loginUserId = SessionUserUtil.getUserId(req.getSession());
                req.setAttribute("loginUserId", loginUserId);
                break;
            case "/detail.board":
                service.detail(req, resp);
                break;
            case "/detailForm.board":
                req.getRequestDispatcher("/WEB-INF/views/detail.jsp").forward(req, resp);
                break; // 추가
            case "/edit.board":
                if(req.getMethod().equals("GET")) {
                    service.edit(req, resp);  // edit 메서드에서 GET 요청 처리
                } else {
                    service.edit(req, resp);  // edit 메서드에서 POST 요청 처리
                }
                break;
            case "/removeForm.board": 
                service.remove(req, resp);
                break;
            case "/commentCreate.board":
                ((BoardServiceImpl) service).commentCreate(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
