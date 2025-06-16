package com.koreait.mvc2.service;

import com.koreait.mvc2.dao.BoardCommentDAO;
import com.koreait.mvc2.dao.BoardDAO;
import com.koreait.mvc2.dto.BoardCommentDTO;
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
    private BoardCommentDAO commentDAO = new BoardCommentDAO();

    @Override
    public void create(HttpServletRequest request,
                       HttpServletResponse response,
                       String originalName,
                       String storedName) {
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

            // 3. 게시글 DTO 설정
            BoardDTO board = new BoardDTO();
            board.setTitle(title);
            board.setContent(content);
            board.setUserid(sessionDto.getUserid());
            board.setNickname(sessionDto.getName());

            // 파일정보 설정
            if (storedName != null && !storedName.isEmpty()) {
                board.setOriginalName(originalName);
                board.setFileName(storedName);
                board.setFilePath("uploads/" + storedName);
            }

            // 4. DAO 호출
            boolean result = dao.create(board);
            if (!result) {
                throw new RuntimeException("게시글 작성에 실패했습니다.");
            }

            // 5. 목록으로 이동
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
            dao.incrementViews(idx);
            BoardDTO board = dao.detail(idx);
            if (board == null) {
                throw new RuntimeException("존재하지 않는 게시글입니다.");
            }

            List<BoardCommentDTO> commentList = commentDAO.getCommentsByBoardIdx(idx);
            request.setAttribute("commentList", commentList);
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
            if ("GET".equals(request.getMethod())) {
                String board_idx = request.getParameter("board_idx");
                if (board_idx == null || board_idx.trim().isEmpty()) {
                    throw new RuntimeException("게시글 번호가 필요합니다.");
                }
                BoardDTO board = dao.detail(Integer.parseInt(board_idx));
                if (board == null) {
                    throw new RuntimeException("존재하지 않는 게시글입니다.");
                }
                HttpSession session = request.getSession();
                MemberDTO sessionDto = (MemberDTO) session.getAttribute("user");
                if (sessionDto == null) throw new RuntimeException("로그인이 필요합니다.");
                if (!sessionDto.getUserid().equals(board.getUserid())) throw new RuntimeException("작성자만 수정할 수 있습니다.");
                request.setAttribute("board", board);
                request.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(request, response);
                return;
            }
            String board_idx = request.getParameter("board_idx");
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            if (board_idx == null || board_idx.trim().isEmpty()) throw new RuntimeException("게시글 번호가 필요합니다.");
            HttpSession session = request.getSession();
            MemberDTO sessionDto = (MemberDTO) session.getAttribute("user");
            if (sessionDto == null) throw new RuntimeException("로그인이 필요합니다.");
            BoardDTO board = dao.detail(Integer.parseInt(board_idx));
            if (!sessionDto.getUserid().equals(board.getUserid())) throw new RuntimeException("작성자만 수정할 수 있습니다.");
            board.setTitle(title);
            board.setContent(content);
            boolean result = dao.edit(board);
            if (!result) throw new RuntimeException("게시글 수정에 실패했습니다.");
            response.sendRedirect(request.getContextPath() + "/detail.board?board_idx=" + board_idx);
        } catch (Exception e) {
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
        BoardDTO originalPost = dao.detail(board_idx);
        if (!originalPost.getUserid().equals(sessionDto.getUserid())) {
            req.setAttribute("error", "작성자만 삭제할 수 있습니다.");
            return;
        }
        boolean success = dao.remove(board_idx);
        if (success) resp.sendRedirect("list.board");
        else req.getRequestDispatcher("/WEB-INF/views/result.jsp").forward(req, resp);
    }

    public void commentCreate(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession();
            MemberDTO user = (MemberDTO) session.getAttribute("user");
            if (user == null) throw new RuntimeException("로그인이 필요합니다.");
            int board_idx = Integer.parseInt(request.getParameter("board_idx"));
            String content = request.getParameter("content");
            BoardCommentDTO dto = new BoardCommentDTO();
            dto.setBoard_idx(board_idx);
            dto.setUser_id(user.getUserid());
            dto.setContent(content);
            boolean result = commentDAO.insertComment(dto);
            if (!result) throw new RuntimeException("댓글 등록에 실패했습니다.");
            response.sendRedirect("detail.board?board_idx=" + board_idx);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
