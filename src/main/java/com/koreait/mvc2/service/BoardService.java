package com.koreait.mvc2.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface BoardService {
    /**
     * 게시글 생성 (파일 업로드 처리 포함)
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @param originalName 클라이언트가 업로드한 실제 파일명 (없으면 null)
     * @param storedName 서버에 저장된 파일명 (없으면 null)
     */
    void create(HttpServletRequest req,
                HttpServletResponse resp,
                String originalName,
                String storedName)
            throws ServletException, IOException;

    void list(HttpServletRequest req,
              HttpServletResponse resp)
            throws ServletException, IOException;

    void detail(HttpServletRequest req,
                HttpServletResponse resp)
            throws ServletException, IOException;

    void incrementViews(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException;

    void edit(HttpServletRequest req,
              HttpServletResponse resp)
            throws ServletException, IOException;

    void remove(HttpServletRequest req,
                HttpServletResponse resp)
            throws ServletException, IOException;
}
