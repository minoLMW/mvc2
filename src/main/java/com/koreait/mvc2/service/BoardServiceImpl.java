package com.koreait.mvc2.service;

import com.koreait.mvc2.dao.BoardDAO;
import com.koreait.mvc2.dto.BoardDTO;
import com.koreait.mvc2.dto.MemberDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class BoardServiceImpl implements BoardService {
    private BoardDAO dao = new BoardDAO();

    @Override
    public void create(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 1. 요청 파라미터 처리
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            
            if (title == null || title.trim().isEmpty()) {
                throw new RuntimeException("제목을 입력해주세요.");
            }
            if (content == null || content.trim().isEmpty()) {
                throw new RuntimeException("내용을 입력해주세요.");
            }
            
            // 2. 세션 체크
            HttpSession session = request.getSession();
            MemberDTO sessionDto = (MemberDTO) session.getAttribute("user");
            
            if (sessionDto == null) {
                throw new RuntimeException("로그인이 필요한 기능입니다.");
            }
            
            // 3. 게시글 작성
            BoardDTO board = new BoardDTO();
            board.setTitle(title);
            board.setContent(content);
            board.setUserid(sessionDto.getUserid());
            board.setNickname(sessionDto.getName());
            boolean result = dao.create(board);
            
            if (!result) {
                throw new RuntimeException("게시글 작성에 실패했습니다.");
            }
            
            // 4. 목록으로 이동
            response.sendRedirect("list.board");
            
        } catch (Exception e) {
           e.printStackTrace();
           
        }
    }

    @Override
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BoardDTO> list = dao.list();
        
        req.setAttribute("list", list);
        req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
    }

    @Override
    public void detail(HttpServletRequest request, HttpServletResponse response) {
        try {
            String board_idx = request.getParameter("board_idx");
            if (board_idx == null || board_idx.trim().isEmpty()) {
                throw new RuntimeException("게시글 번호가 필요합니다.");
            }

            int idx = Integer.parseInt(board_idx);
            
            // 조회수 증가
            dao.incrementViews(idx);
            
            // 게시글 조회
            BoardDTO board = dao.detail(idx);
            if (board == null) {
                throw new RuntimeException("존재하지 않는 게시글입니다.");
            }
            
            request.setAttribute("board", board);
            request.getRequestDispatcher("/WEB-INF/views/detail.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void incrementViews(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int board_idx = Integer.parseInt(req.getParameter("board_idx"));
        dao.incrementViews(board_idx);
    }

    @Override
    public void edit(HttpServletRequest request, HttpServletResponse response) {
        try {
            // GET 요청인 경우 (수정 폼으로 이동)
            if (request.getMethod().equals("GET")) {
                String board_idx = request.getParameter("board_idx");
                if (board_idx == null || board_idx.trim().isEmpty()) {
                    throw new RuntimeException("게시글 번호가 필요합니다.");
                }

                // 게시글 정보 조회
                BoardDTO board = dao.detail(Integer.parseInt(board_idx));
                if (board == null) {
                    throw new RuntimeException("존재하지 않는 게시글입니다.");
                }

                // 세션 체크
                HttpSession session = request.getSession();
                MemberDTO sessionDto = (MemberDTO) session.getAttribute("user");
                if (sessionDto == null) {
                    throw new RuntimeException("로그인이 필요한 기능입니다.");
                }

                // 작성자 체크
                if (!sessionDto.getUserid().equals(board.getUserid())) {
                    throw new RuntimeException("작성자만 수정할 수 있습니다.");
                }

                request.setAttribute("board", board);
                request.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(request, response);
                return;
            }

            // POST 요청인 경우 (수정 처리)
            // 1. 요청 파라미터 처리
            String board_idx = request.getParameter("board_idx");
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            
            
            if (board_idx == null || board_idx.trim().isEmpty()) {
                throw new RuntimeException("게시글 번호가 필요합니다.");
            }
            
            // 2. 세션 체크
            HttpSession session = request.getSession();
            MemberDTO sessionDto = (MemberDTO) session.getAttribute("user");
            
            if (sessionDto == null) {
                throw new RuntimeException("로그인이 필요한 기능입니다.");
            }
            
            // 3. 게시글 정보 조회
            BoardDTO board = dao.detail(Integer.parseInt(board_idx));
            
            if (board == null) {
                throw new RuntimeException("존재하지 않는 게시글입니다.");
            }
            
            // 4. 작성자 체크
            if (!sessionDto.getUserid().equals(board.getUserid())) {
                throw new RuntimeException("작성자만 수정할 수 있습니다.");
            }
            
            // 5. 게시글 수정
            board.setTitle(title);
            board.setContent(content);
            boolean result = dao.edit(board);
           
            if (!result) {
                throw new RuntimeException("게시글 수정에 실패했습니다.");
            }
            
            // 6. 상세보기로 이동
            response.sendRedirect(request.getContextPath() + "/detail.board?board_idx=" + board_idx);
            
        } catch (Exception e) {
            System.out.println("edit 메서드 에러: " + e.getMessage());
            e.printStackTrace();
           
        }
    }

    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberDTO sessionDto = (MemberDTO) req.getSession().getAttribute("user");
        if (sessionDto == null) {
            req.setAttribute("error", "로그인이 필요합니다.");
            return;
        }

        int board_idx = Integer.parseInt(req.getParameter("board_idx"));
        
        // 게시글 작성자 확인
        BoardDTO originalPost = dao.detail(board_idx);
        if (originalPost == null) {
            req.setAttribute("error", "존재하지 않는 게시글입니다.");
            return;
        }
        
        if (!originalPost.getUserid().equals(sessionDto.getUserid())) {
            req.setAttribute("error", "작성자만 삭제할 수 있습니다.");
            return;
        }

        boolean success = dao.remove(board_idx);
        if(success) {
            resp.sendRedirect("list.board");
        } else {
            req.setAttribute("success", false);
            req.getRequestDispatcher("/WEB-INF/views/result.jsp").forward(req, resp);
        }
    }
}
