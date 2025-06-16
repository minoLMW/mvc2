package com.koreait.mvc2.controller;

import com.koreait.mvc2.dto.KakaoDTO;
import com.koreait.mvc2.service.KakaoService;
import com.koreait.mvc2.service.KakaoServiceImpl;
import com.koreait.mvc2.service.MemberService;
import com.koreait.mvc2.service.MemberServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("*.member")
public class MemberController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MemberService service = new MemberServiceImpl();
    private KakaoService kakaoService = new KakaoServiceImpl();

    public MemberController() {}

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
            case "/join.member":
                req.getRequestDispatcher("/WEB-INF/views/join.jsp").forward(req, resp);
                break;
            case "/joinForm.member":
                service.join(req, resp);
                break;
            case "/login.member":
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
                break;
            case "/loginForm.member":
                service.login(req, resp);
                break;
            case "/logout.member":
                HttpSession session = req.getSession();
                session.invalidate();
                resp.sendRedirect(req.getContextPath() + "/login.member");
                break;
            case "/mypage.member":
                req.getRequestDispatcher("/WEB-INF/views/mypage.jsp").forward(req, resp);
                break;
            case "/modifyForm.member":
                if (req.getMethod().equals("GET")) {
                    req.getRequestDispatcher("/WEB-INF/views/modify.jsp").forward(req, resp);
                } else {
                    service.modify(req, resp);
                }
                break;
            case "/editForm.member":
                if (req.getMethod().equals("GET")) {
                    req.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(req, resp);
                } else {
                    service.modify(req, resp);
                }
                break;
            case "/createForm.member":
                if (req.getMethod().equals("GET")) {
                    req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, resp);
                } else {
                    service.modify(req, resp);
                }
                break;
            case "/deleteForm.member":
                service.delete(req, resp);
                break;
            // 새로 추가: 카카오 로그인 시작 URL로 리다이렉트
            case "/kakaoLogin.member":
                // 카카오 인증 페이지로 보낼 URL 생성
                String authUrl = kakaoService.getAuthUrl();
                resp.sendRedirect(authUrl);
                break;
            case "/kakao.member":
                String code = req.getParameter("code");
                if (code == null || code.isEmpty()) {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Authorization code is missing");
                    return;
                }
                String accessToken = kakaoService.getAccessToken(code);
                KakaoDTO kakaoUser = kakaoService.getUserInfo(accessToken);
                // 세션 저장 또는 회원 가입/로그인 처리
                kakaoService.registerOrLogin(kakaoUser, req.getSession());
                req.setAttribute("kakaoUser", kakaoUser);
                // 디버깅 로그
                System.out.println("카카오 사용자 닉네임: " + kakaoUser.getNickname());
                System.out.println("카카오 이메일: " + kakaoUser.getEmail());
                req.getRequestDispatcher("/WEB-INF/views/result.jsp").forward(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
