package com.koreait.mvc2.controller;

import com.koreait.mvc2.service.BoardService;
import com.koreait.mvc2.service.BoardServiceImpl;
import com.koreait.mvc2.util.SessionUserUtil;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,      // 1MB
        maxFileSize = 1024 * 1024 * 10,       // 10MB
        maxRequestSize = 1024 * 1024 * 15     // 15MB
)
@WebServlet("*.board")
public class BoardController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BoardService service = new BoardServiceImpl();
    private static final String UPLOAD_DIR = "uploads";

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
                // 파일 처리
                Part filePart = req.getPart("file");
                String originalName = null;
                String storedName = null;
                if (filePart != null && filePart.getSize() > 0) {
                    originalName = filePart.getSubmittedFileName();
                    String ext = originalName.substring(originalName.lastIndexOf('.'));
                    storedName = UUID.randomUUID().toString() + ext;
                    String uploadPath = getServletContext().getRealPath("/uploads");
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) uploadDir.mkdirs();
                    filePart.write(uploadPath + File.separator + storedName);
                }
                // 서비스 호출 (원본명, 저장명 전달)
                service.create(req, resp, originalName, storedName);
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
                break;
            case "/edit.board":
                service.edit(req, resp);
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
