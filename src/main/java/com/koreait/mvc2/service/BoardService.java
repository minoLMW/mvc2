package com.koreait.mvc2.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface BoardService {
    void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void incrementViews(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
    void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
