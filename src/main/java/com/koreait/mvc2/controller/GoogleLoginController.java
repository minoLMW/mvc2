package com.koreait.mvc2.controller;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

public class GoogleLoginController extends HttpServlet {

    private static final String CLIENT_ID = "1068789889786-drr2n0fge0ctrn6p0pgetdaguocq0e0p.apps.googleusercontent.com"; // 예: 1234567890-xxxxx.apps.googleusercontent.com
    private static final String REDIRECT_URI = "http://localhost:8081/mvc2_war/oauth2callback";
    private static final String SCOPE = "https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String authUrl = "https://accounts.google.com/o/oauth2/v2/auth"
                + "?client_id=" + CLIENT_ID
                + "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI, "UTF-8")
                + "&response_type=code"
                + "&scope=" + URLEncoder.encode(SCOPE, "UTF-8")
                + "&access_type=offline"
                + "&prompt=consent";

        response.sendRedirect(authUrl);
    }
}