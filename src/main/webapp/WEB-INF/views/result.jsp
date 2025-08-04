<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>로그인 결과</title>
</head>
<body>

<c:choose>

  <%-- 회원가입 결과 --%>
  <c:when test="${not empty success}">
    <h2>
      <c:choose>
        <c:when test="${success}">회원가입이 성공적으로 처리되었습니다!</c:when>
        <c:otherwise>회원가입 중 문제가 발생했습니다.</c:otherwise>
      </c:choose>
    </h2>
    <p><a href="mypage.member">마이페이지</a></p>
  </c:when>

  <%-- 일반 로그인 --%>
  <c:when test="${not empty loginUser}">
    <h2>${loginUser.userid} (${loginUser.name})님, 로그인 성공!</h2>
    <p><a href="mypage.member">마이페이지</a> | <a href="logout.member">로그아웃</a></p>
  </c:when>

  <%-- 카카오 로그인 --%>
  <c:when test="${not empty kakaoUser}">
    <h2>${kakaoUser.nickname} 님, 카카오 로그인 성공!</h2>
    <p>이메일: ${kakaoUser.email}</p>
    <p><a href="mypage.member">마이페이지</a></p>
  </c:when>

  <%-- 구글 로그인 --%>
  <c:when test="${not empty user}">
    <h2>${user.name}님, 구글 로그인 성공!</h2>
    <p>이메일: ${user.email}</p>

    <c:if test="${not empty user.picture}">
      <img src="${user.picture}" width="100" height="100" />
    </c:if>

    <p><a href="mypage.member">마이페이지</a> | <a href="logout.member">로그아웃</a></p>
  </c:when>

  <%-- 로그인 실패 --%>
  <c:otherwise>
    <h2>로그인 실패</h2>
    <p>아이디 또는 비밀번호를 확인하세요 🤢🤢🤢</p>
  </c:otherwise>

</c:choose>

</body>
</html>
