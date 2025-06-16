package com.koreait.mvc2.service;

import com.koreait.mvc2.dao.BoardCommentDAO;
import com.koreait.mvc2.dao.BoardDAO;
import com.koreait.mvc2.dto.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

import static com.koreait.mvc2.util.SessionUserUtil.getUserIdAndNickname;

public class BoardServiceImpl implements BoardService {
    private final BoardDAO dao = new BoardDAO();
    private final BoardCommentDAO commentDAO = new BoardCommentDAO();

    // 게시글 작성
    @Override
    public void create(HttpServletRequest request,
            HttpServletResponse response,
            String originalName,
            String storedName) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            if (session.getAttribute("user") == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            String title = request.getParameter("title");
            String content = request.getParameter("content");

            if (title == null || title.trim().isEmpty()) {
                throw new RuntimeException("제목을 입력해주세요.");
            }
            if (content == null || content.trim().isEmpty()) {
                throw new RuntimeException("내용을 입력해주세요.");
            }

            String[] userInfo = getUserIdAndNickname(session);


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
            
            // 4. 목록으로 이동
            response.sendRedirect("list.board");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("게시글 작성 중 오류 발생");
        }
    }

    // 게시글 목록
    @Override
    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BoardDTO> list = dao.list();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
    }

    // 게시글 상세
    @Override
    public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idx = Integer.parseInt(request.getParameter("board_idx"));
            dao.incrementViews(idx);

            BoardDTO board = dao.detail(idx);
            if (board == null) throw new RuntimeException("존재하지 않는 게시글입니다.");

            List<BoardCommentDTO> commentList = commentDAO.getCommentsByBoardIdx(idx);

            request.setAttribute("board", board);
            request.setAttribute("commentList", commentList);
            request.getRequestDispatcher("/WEB-INF/views/detail.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("게시글 상세 보기 중 오류 발생");
        }
    }

    // 조회수 증가
    @Override
    public void incrementViews(HttpServletRequest req, HttpServletResponse resp) {
        int board_idx = Integer.parseInt(req.getParameter("board_idx"));
        dao.incrementViews(board_idx);
    }

    // 게시글 수정
    @Override
    public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String[] userInfo = getUserIdAndNickname(session);
            String userId = userInfo[0];

            if (request.getMethod().equals("GET")) {
                int board_idx = Integer.parseInt(request.getParameter("board_idx"));
                BoardDTO board = dao.detail(board_idx);
                if (board == null || !board.getUserid().equals(userId)) {
                    throw new RuntimeException("수정 권한이 없습니다.");
                }
                request.setAttribute("board", board);
                request.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(request, response);
                return;
            }

            int board_idx = Integer.parseInt(request.getParameter("board_idx"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");

            BoardDTO board = dao.detail(board_idx);
            if (board == null || !board.getUserid().equals(userId)) {
                throw new RuntimeException("수정 권한이 없습니다.");
            }

            if (board_idx == null || board_idx.trim().isEmpty()) throw new RuntimeException("게시글 번호가 필요합니다.");
            HttpSession session = request.getSession();
            MemberDTO sessionDto = (MemberDTO) session.getAttribute("user");
            if (sessionDto == null) throw new RuntimeException("로그인이 필요합니다.");
            BoardDTO board = dao.detail(Integer.parseInt(board_idx));
            if (!sessionDto.getUserid().equals(board.getUserid())) throw new RuntimeException("작성자만 수정할 수 있습니다.");
            board.setTitle(title);
            board.setContent(content);
            if (!dao.edit(board)) {
                throw new RuntimeException("게시글 수정 실패");
            }

            response.sendRedirect("detail.board?board_idx=" + board_idx);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("게시글 수정 중 오류 발생");
        }
    }

    // 게시글 삭제
    @Override
    public void remove(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int board_idx = Integer.parseInt(req.getParameter("board_idx"));
            HttpSession session = req.getSession();
            String[] userInfo = getUserIdAndNickname(session);
            String userId = userInfo[0];

            BoardDTO board = dao.detail(board_idx);
            if (board == null || !board.getUserid().equals(userId)) {
                throw new RuntimeException("삭제 권한이 없습니다.");
            }

            if (!dao.remove(board_idx)) {
                throw new RuntimeException("삭제 실패");
            }
            resp.sendRedirect("list.board");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("게시글 삭제 중 오류 발생");
        }
    }

    // 댓글 등록
    public void commentCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession();
            MemberDTO user = (MemberDTO) session.getAttribute("user");
            if (user == null) throw new RuntimeException("로그인이 필요합니다.");
            int board_idx = Integer.parseInt(request.getParameter("board_idx"));
            String content = request.getParameter("content");
            BoardCommentDTO dto = new BoardCommentDTO();
            dto.setBoard_idx(board_idx);
            dto.setUser_id(userInfo[0]);
            dto.setContent(content);

            if (!commentDAO.insertComment(dto)) {
                throw new RuntimeException("댓글 등록 실패");
            }

            response.sendRedirect("detail.board?board_idx=" + board_idx);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("댓글 등록 중 오류 발생");
        }
    }
}
