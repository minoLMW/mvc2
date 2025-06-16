<%--
  Created by IntelliJ IDEA.
  User: minwo
  Date: 2025-06-13
  Time: 오후 4:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <!--<head>
    <title>Title</title>
  </head>
  <body>
  -->
  </body>
<head>
    <title>게시글 상세</title>
    <style>
        * {margin:0; padding:0;}
        body {padding:0 25px; background-color:#001b3b; color:#fff;}
        h2 {display:flex; justify-content:center; align-items:center; padding:25px 50px 25px 25px; font-size:50px;}
        h2::after {content:''; display:inline-block; width:75px; height:75px;  background:url('https://www.notion.so/_assets/a7e5ea83ae6a4462.gif') no-repeat center right / 75px auto;;}
        .content {max-width:500px; margin:0 auto; background-color:rgba(255,255,255,0.1); padding:20px; border-radius:12px;}
        .content p {margin:10px 0; font-size:18px;}
        .content .title {font-size:24px; font-weight:bold; margin-bottom:20px;}
        .content .info {color:#cdb4db; font-size:14px; margin-bottom:20px;}
        .content .text {line-height:1.6; margin:20px 0;}
        .button {margin-top:20px; text-align:center;}
        .button a {display:inline-block; width:160px; height:50px; line-height:50px; border-radius:12px; font-size:20px; border:none; background-color:#cdb4db; color:#fff; text-decoration:none; margin:0 10px;}
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
            0% {
                background-position: 0em 0em, 0em 0em, 0em 0em;
            }
            100% {
                background-position: -50em 200em, 0em 50em, 50em 100em;
            }
        }
        .container {width: calc(100% - 50px); margin: 0 auto;}
        .board-info {margin-bottom: 20px; padding: 15px; background-color: #f8f9fa; border-radius: 5px;}
        .board-info p {margin: 5px 0; color: #666;}
        .board-content {margin: 20px 0; padding: 20px; background-color: #fff; border: 1px solid #e6e6e6; border-radius: 5px; min-height: 200px;}
        .board-content p {line-height: 1.6; color: #333;}
        .button-group {margin-top: 20px; text-align: right;}
        .button-group a {display: inline-block; padding: 10px 20px; margin-left: 10px; background-color: #cdb4db; color: white; text-decoration: none; border-radius: 5px; transition: background-color 0.3s;}
        .button-group a:hover {background-color: #b8a0c8;}
        .button-group a.disabled {background-color: #e0e0e0; color: #999; cursor: not-allowed; pointer-events: none;}
        .button-group a.back {background-color: #6c757d;}
        .button-group a.back:hover {background-color: #5a6268;}
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
        <div class="button-group">
            <c:if test="${sessionScope.user.userid eq board.userid}">
                <a href="edit.board?board_idx=${board.board_idx}">수정</a>
                <a href="removeForm.board?board_idx=${board.board_idx}">삭제</a>
            </c:if>
            <c:if test="${sessionScope.user.userid ne board.userid}">
                <a href="#" class="disabled">수정</a>
                <a href="#" class="disabled">삭제</a>
            </c:if>
            <a href="list.board" class="back">목록</a>
        </div>
        <!-- ✅ 댓글 영역 -->
        <div class="comment-section">
            <h3>댓글</h3>

            <!-- 🔹 댓글 목록 출력 -->
            <c:forEach var="comment" items="${commentList}">
                <div class="comment">
                    <strong>${comment.user_id}</strong> | <span>${comment.created_at}</span>
                    <p>${comment.content}</p>
                </div>
            </c:forEach>

            <!-- 🔹 댓글 작성 폼 (로그인한 사용자만) -->
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
