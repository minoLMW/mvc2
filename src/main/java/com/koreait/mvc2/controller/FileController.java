package com.koreait.mvc2.controller;

import com.koreait.mvc2.dto.FileDTO;
import com.koreait.mvc2.service.FileService;
import com.koreait.mvc2.service.FileServiceImpl;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class FileController extends HttpServlet {
    private FileService fileService;

    @Override
    public void init() throws ServletException {
        fileService = new FileServiceImpl(getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("/upload".equals(req.getPathInfo())) {
            int boardIdx = Integer.parseInt(req.getParameter("boardIdx"));
            try {
                fileService.upload(boardIdx, req.getPart("file"));
                resp.sendRedirect(req.getContextPath() + "/board/detail?boardIdx=" + boardIdx);
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("/download".equals(req.getPathInfo())) {
            int fileIdx = Integer.parseInt(req.getParameter("fileIdx"));
            try {
                FileDTO f = fileService.getFile(fileIdx);
                String full = getServletContext().getRealPath("/uploads")
                        + File.separator + f.getStoredName();
                // 1) MIME 타입 설정
                resp.setContentType(f.getContentType());
                // 2) inline으로 열도록 헤더 설정
                resp.setHeader("Content-Disposition",
                        "inline; filename=\"" + f.getOriginalName() + "\"");
                // 3) 파일 스트리밍
                Files.copy(Paths.get(full), resp.getOutputStream());
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }
    }
}
