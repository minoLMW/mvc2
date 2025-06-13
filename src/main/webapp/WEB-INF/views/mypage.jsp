<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    com.koreait.mvc2.dto.MemberDTO user = (com.koreait.mvc2.dto.MemberDTO)
    session.getAttribute("user");
%>
<html>
<head>
    <title>마이페이지</title>
</head>
<body>
<h2>${user.name}님 마이페이지</h2>
<p>아이디: ${user.userid}</p>
<p>이메일: ${user.email}</p>
<form method="get" action="modifyForm.member">
  <button type="submit">회원정보 수정</button>
</form>
<form method="get" action="list.board">
  <button type="submit">게시글 목록</button>
</form>
<form method="post" action="delete.member" onsubmit="return confirm('정말 탈퇴하시겠습니까?')">
  <button type="submit">회원 탈퇴</button>
</form>
<a href="logout.member">로그아웃</a>
</body>
</html>
