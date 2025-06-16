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
    <style>
		@import url("https://fonts.googleapis.com/css2?family=Quicksand:wght@300&display=swap");
		* {margin: 0;padding: 0;box-sizing: border-box;font-family: "Quicksand", sans-serif;}
		body {display: flex;justify-content: center;align-items: center;min-height: 100vh;background: #111;width: 100%;overflow: hidden;}
		.ring {position: absolute;right:50%; top:50%; transform:translate(50%, -50%); width:500px;height:500px;display: flex;justify-content: center;align-items: center; animation:effect 1s 2s ease-in forwards;}
		.ring i {position: absolute;inset: 0;border: 2px solid #fff;transition: 0.5s;}
		.ring i:nth-child(1) {border-radius: 38% 62% 63% 37% / 41% 44% 56% 59%;animation: animate 6s linear infinite;}
		.ring i:nth-child(2) {border-radius: 41% 44% 56% 59%/38% 62% 63% 37%;animation: animate 4s linear infinite;}
		.ring i:nth-child(3) {border-radius: 41% 44% 56% 59%/38% 62% 63% 37%;animation: animate2 10s linear infinite;}
        @keyframes effect {
            0%{right:50%;transform:translate(50%, -50%);}
            100%{right:100px;transform:translate(0, -50%);}
        }
		@keyframes animate {
			0% {transform: rotate(0deg); border:2px solid #fff;}
            50% {
                border: 6px solid var(--clr);
                filter: drop-shadow(0 0 20px var(--clr));
            }
			100% {transform: rotate(360deg); border:2px solid #fff;}
		}
		@keyframes animate2 {
			0% {transform: rotate(360deg); border:2px solid #fff;}
            50% {
                border: 6px solid var(--clr);
                filter: drop-shadow(0 0 20px var(--clr));
            }
			100% {transform: rotate(0deg); border:2px solid #fff;}
		}
        .glass-card {display:flex; justify-content:center; align-items:center; overflow:hidden; position:relative; width:550px; height:650px; border-top:1px solid rgba(255,255,255,0.5); border-left:1px solid rgba(255,255,255,0.5); border-radius:15px; background:rgba(255,255,255,0.1); box-shadow:20px 20px 50px rgba(0,0,0,0.5); backdrop-filter:blur(50px); animation: glassCard 1.5s 3s linear forwards; /* opacity:0; */}
		.glass-card .contents {padding:20px; text-align:center; transition:0.5s;}
        /* @keyframes glassCard {
            0%{opacity:0;}
            100%{opacity:1;}
        } */
        .glass-card h2 {color:#fff; font-size:30px;}
        .glass-card p {margin-top:30px; color:#fff; font-size:22px;}
        .glass-card p + p {margin-top:0;}
        .glass-card .buttons {display:flex; flex-wrap:wrap; justify-content:center; gap:20px; width:100%;}
        .glass-card a,
        .glass-card button {display:inline-block;position:relative;padding:8px 20px;margin-top:15px;background-color:#fff;border:none;border-radius:20px;color:#000;font-weight:bold;text-decoration:none;cursor:pointer;}
        .glass-card a {margin-top:50px; background-color:#000; color:#fff;}
    </style>
</head>
<body>
<div class="ring">
	<i style="--clr:#00ff0a;"></i>
	<i style="--clr:#ff0057;"></i>
	<i style="--clr:#fffd44;"></i>
</div>
<div class="glass-card">
    <div class="contents">

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
    </div>
</div>
</body>
</html>
