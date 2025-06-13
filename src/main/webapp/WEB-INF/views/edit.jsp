<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  com.koreait.mvc2.dto.BoardDTO boardUser = (com.koreait.mvc2.dto.BoardDTO) session.getAttribute("boardUser");
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>회원정보 수정</h2>
<form method="post" action="modifyForm.board">
  <p>작성자: ${boardUser.userid}</p>
  <p>제목: <input type="text" name="title" value="${boardUser.title}"></p>
  <p>내용: <input type="text" name="content" value="${boardUser.content}"></p>
  <p>작성일: <input type="text" name="created_at" value="${boardUser.created_at}"></p>
  <p>조회수: <input type="number" name="view_count" value="${boardUser.view_count}"></p>
  <p><button type="submit">작성 완료</button></p>
</form>
</body>
</html>
