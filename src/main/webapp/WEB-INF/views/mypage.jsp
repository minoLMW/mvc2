<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    Object userObj = session.getAttribute("user");

    if (userObj instanceof com.koreait.mvc2.dto.MemberDTO) {
        com.koreait.mvc2.dto.MemberDTO user = (com.koreait.mvc2.dto.MemberDTO) userObj;
        request.setAttribute("member", user);
    } else if (userObj instanceof com.koreait.mvc2.dto.KakaoDTO) {
        com.koreait.mvc2.dto.KakaoDTO kakaoUser = (com.koreait.mvc2.dto.KakaoDTO) userObj;
        request.setAttribute("kakao", kakaoUser);
    } else if (userObj instanceof com.koreait.mvc2.dto.GoogleDTO) {
        com.koreait.mvc2.dto.GoogleDTO googleUser = (com.koreait.mvc2.dto.GoogleDTO) userObj;
        request.setAttribute("google", googleUser);
    }
%>
<html>
<head>
    <title>마이페이지</title>
</head>
<body>

<h2>
    <c:choose>
        <c:when test="${not empty member}">${member.name}님 마이페이지</c:when>
        <c:when test="${not empty kakao}">${kakao.nickname}님 마이페이지</c:when>
        <c:when test="${not empty google}">${google.name}님 마이페이지</c:when>
    </c:choose>
</h2>

<p>
    <c:if test="${not empty member}">아이디: ${member.userid}</c:if>
</p>

<p>
    <c:choose>
        <c:when test="${not empty member}">이메일: ${member.email}</c:when>
        <c:when test="${not empty kakao}">이메일: ${kakao.email}</c:when>
        <c:when test="${not empty google}">이메일: ${google.email}</c:when>
    </c:choose>
</p>

<!-- 일반 회원만 회원정보 수정 및 탈퇴 가능 -->
<c:if test="${not empty member}">
    <form method="get" action="modifyForm.member"><button type="submit">회원정보 수정</button></form>
    <form method="get" action="createForm.member"><button type="submit">게시물 작성</button></form>
    <form method="get" action="list.board"><button type="submit">게시글 목록</button></form>
    <form method="post" action="delete.member" onsubmit="return confirm('정말 탈퇴하시겠습니까?')">
        <button type="submit">회원 탈퇴</button>
    </form>
</c:if>
<!-- 카카오/구글은 회원정보 수정 불가 -->
<c:if test="${not empty kakao || not empty google}">
    <form method="get" action="createForm.member"><button type="submit">게시물 작성</button></form>
    <form method="get" action="list.board"><button type="submit">게시글 목록</button></form>
    <form method="post" action="delete.member" onsubmit="return confirm('정말 탈퇴하시겠습니까?')">
        <button type="submit">회원 탈퇴</button>
    </form>
</c:if>

<a href="logout.member">로그아웃</a>

</body>
</html>
