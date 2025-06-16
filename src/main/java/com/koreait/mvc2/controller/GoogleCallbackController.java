package com.koreait.mvc2.controller;

import com.google.api.services.oauth2.model.Userinfoplus;
import com.koreait.mvc2.dto.GoogleDTO;
import com.koreait.mvc2.service.GoogleAuthService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class GoogleCallbackController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String code = req.getParameter("code");

        if (code == null || code.isEmpty()) {
            resp.sendRedirect("error.jsp");
            return;
        }

        try {
            GoogleAuthService authService = new GoogleAuthService();
            Userinfoplus userInfo = authService.getUserInfo(code);

            GoogleDTO googleUser = new GoogleDTO(userInfo.getName(), userInfo.getEmail(), userInfo.getPicture());

            HttpSession session = req.getSession();
            session.setAttribute("user", googleUser);
            req.setAttribute("user", googleUser);

            req.getRequestDispatcher("/WEB-INF/views/result.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("error.jsp");
        }
    }
}
