<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 상세</title>
    <style>
        * {margin:0; padding:0;}
        body {padding:0 25px; background-color:#001b3b; color:#fff;}
        h2 {display:flex; justify-content:center; align-items:center; padding:25px 50px 25px 25px; font-size:50px;}
        h2::after {content:''; display:inline-block; width:75px; height:75px; background:url('https://www.notion.so/_assets/a7e5ea83ae6a4462.gif') no-repeat center right / 75px auto;}
        .container {width: calc(100% - 50px); margin: 0 auto;}
        .board-info {margin-bottom: 20px; padding: 15px; background-color: rgba(255,255,255,0.1); border-radius: 5px;}
        .board-info p {margin: 5px 0; color: #cdb4db; font-size: 16px;}
        .board-info p strong {color:#fff;}
        .board-content {margin: 20px 0; padding: 20px; background-color: rgba(255,255,255,0.2); border-radius: 5px; min-height: 200px;}
        .board-content p {line-height: 1.6; color: #fff;}
        .file-section {margin: 20px 0;}
        .file-section h3 {font-size: 20px; margin-bottom: 10px; color: #cdb4db;}
        .file-item {margin: 5px 0;}
        .file-item a {color: #fff; text-decoration: underline;}
        .file-item span {font-size: 12px; color: #cdb4db; margin-left: 5px;}
        .button-group {margin-top: 20px; text-align: right;}
        .button-group a {display: inline-block; padding: 10px 20px; margin-left: 10px; background-color: #cdb4db; color: #fff; text-decoration: none; border-radius: 5px; transition: background-color 0.3s;}
        .button-group a:hover {background-color: #b8a0c8;}
        .button-group a.disabled {background-color: #555; color: #999; cursor: not-allowed; pointer-events: none;}
        .button-group a.back {background-color: #6c757d;}
        .button-group a.back:hover {background-color: #5a6268;}
        .comment-section {margin-top: 40px;}
        .comment-section h3 {color: #cdb4db; margin-bottom: 10px;}
        .comment {background-color: rgba(255,255,255,0.1); padding: 10px; border-radius: 5px; margin-bottom: 10px;}
        .comment strong {color: #fff;}
        .comment span {font-size: 12px; color: #cdb4db; margin-left: 5px;}
        .comment p {color: #fff; margin-top: 5px;}
        .comment-form {margin-top: 20px;}
        .comment-form textarea {width: 100%; height: 80px; padding: 8px; border-radius: 5px; border: none; resize: none;}
        .comment-form button {margin-top: 10px; padding: 8px 16px; background-color: #cdb4db; border: none; border-radius: 5px; color: #fff; cursor: pointer;}
        /* 눈 내리는 배경 */
        .snowing {
            background-image:
                    url('https://currys-ssl.cdn.dixons.com/css/themes/email/2017-2018/wk35/XmasDayPC/V1/_snow.png'),
                    url('https://currys-ssl.cdn.dixons.com/css/themes/email/2017-2018/wk35/XmasDayPC/V1/_snow2.png'),
                    url('https://currys-ssl.cdn.dixons.com/css/themes/email/2017-2018/wk35/XmasDayPC/V1/_snow3.png');
            background-repeat: repeat;
            background-attachment: fixed;
            animation: snow 20s steps(600) infinite;
        }
        @keyframes snow {
            0% {background-position: 0em 0em, 0em 0em, 0em 0em;}
            100% {background-position: -50em 200em, 0em 50em, 50em 100em;}
        }
    </style>
</head>
<body class="snowing">
<h2>게시글 상세</h2>
<div class="container">
    <div class="board-info">
        <p><strong>제목:</strong> ${board.title}</p>
        <p><strong>작성자:</strong> ${board.nickname}</p>
        <p><strong>작성일:</strong> ${board.regdate}</p>
        <p><strong>조회수:</strong> ${board.view_count}</p>
    </div>
    <div class="board-content">
        <p>${board.content}</p>
    </div>
    <!-- 게시글 본문 아래에 파일 첨부 섹션 추가 -->
    <c:if test="${not empty board.fileName}">
        <div class="file-section">
            <h3>첨부 파일</h3>
            <div class="file-item">
                <a href="${pageContext.request.contextPath}/uploads/${board.fileName}" target="_blank">
                        ${board.originalName}
                </a>
                <span>(${board.fileSize} bytes)</span>
            </div>
        </div>
    </c:if>

    <div class="button-group">
        <c:if test="${sessionScope.user.userid eq board.userid}">
            <a href="edit.board?board_idx=${board.board_idx}">수정</a>
            <a href="removeForm.board?board_idx=${board.board_idx}">삭제</a>
        </c:if>
        <c:if test="${sessionScope.user.userid ne board.userid}">
            <a class="disabled">수정</a>
            <a class="disabled">삭제</a>
        </c:if>
        <a href="list.board" class="back">목록</a>
    </div>
    <div class="comment-section">
        <h3>댓글</h3>
        <c:forEach var="comment" items="${commentList}">
            <div class="comment">
                <strong>${comment.user_id}</strong><span>${comment.created_at}</span>
                <p>${comment.content}</p>
            </div>
        </c:forEach>
        <c:if test="${not empty sessionScope.user}">
            <form action="commentCreate.board" method="post" class="comment-form">
                <input type="hidden" name="board_idx" value="${board.board_idx}" />
                <textarea name="content" placeholder="댓글을 입력하세요"></textarea>
                <button type="submit">댓글 등록</button>
            </form>
        </c:if>
        <c:if test="${empty sessionScope.user}">
            <p>댓글을 작성하려면 <a href="login.jsp">로그인</a>이 필요합니다.</p>
        </c:if>
    </div>
</div>
</body>
</html>
