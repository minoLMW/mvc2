<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
<h2>로그인</h2>
<form method="post" action="loginForm.member">
    <input type="hidden" name="action" value="login">
    <p>아이디: <input type="text" name="userid"></p>
    <p>비밀번호: <input type="password" name="userpw"></p>
    <p><button type="submit">로그인</button></p>
</form>

<h3>카톡 로그인</h3>
<div class="kakao-btn">
    <button onclick="kakaoLogin()">카카오톡으로 간편로그인</button>
</div>
<h3>구글 로그인</h3>
<p>
    <a href="${pageContext.request.contextPath}/login/google">
        <img src="https://developers.google.com/identity/images/btn_google_signin_light_normal_web.png"
             alt="Google 로그인" />
    </a>
</p>

<script>
    function kakaoLogin() {
        location.href = "https://kauth.kakao.com/oauth/authorize" +
            "?client_id=7d182da6678fff468dfa3be56fcca737" +
            "&redirect_uri=http://localhost:8081/mvc2_war/kakao.member" +
            "&response_type=code" +
            "&prompt=login";  // 항상 계정 선택 가능하도록 강제 로그인 창 띄움
    }
</script>

</body>
</html>
