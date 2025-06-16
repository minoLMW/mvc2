<html>
<head>
	<title>login</title>
	<style>
		@import url("https://fonts.googleapis.com/css2?family=Quicksand:wght@300&display=swap");
		* {margin: 0;padding: 0;box-sizing: border-box;font-family: "Quicksand", sans-serif;}
		body {display: flex;justify-content: center;align-items: center;min-height: 100vh;background: #111;width: 100%;overflow: hidden;}
		.ring {position: relative;width: 500px;height: 500px;display: flex;justify-content: center;align-items: center;}
		.ring i {position: absolute;inset: 0;border: 2px solid #fff;transition: 0.5s;}
		.ring i:nth-child(1) {border-radius: 38% 62% 63% 37% / 41% 44% 56% 59%;animation: animate 6s linear infinite;}
		.ring i:nth-child(2) {border-radius: 41% 44% 56% 59%/38% 62% 63% 37%;animation: animate 4s linear infinite;}
		.ring i:nth-child(3) {border-radius: 41% 44% 56% 59%/38% 62% 63% 37%;animation: animate2 10s linear infinite;}
		.ring:hover i {border: 6px solid var(--clr);filter: drop-shadow(0 0 20px var(--clr));}
		@keyframes animate {
			0% {transform: rotate(0deg);}
			100% {transform: rotate(360deg);}
		}
		@keyframes animate2 {
			0% {transform: rotate(360deg);}
			100% {transform: rotate(0deg);}
		}
		.login {position: absolute;width: 300px;height: 100%;display: flex;justify-content: center;align-items: center;flex-direction: column;gap: 20px;}
		.login h2 {font-size: 2em;color: #fff;}
		.login .inputBx {position: relative;width: 100%;}
		.login .inputBx input,
		.login .inputBx button {position: relative;width: 100%;padding: 12px 20px;background: transparent;border: 2px solid #fff;border-radius: 40px;font-size: 1.2em;color: #fff;box-shadow: none;outline: none;}
		.login .inputBx button[type="submit"] {width: 100%;background: #0078ff;background: linear-gradient(45deg, #ff357a, #fff172);border: none;cursor: pointer;}
		.login .inputBx input::placeholder {color: rgba(255, 255, 255, 0.75);}
		.login .links {position: relative;width: 100%;display: flex;align-items: center;justify-content: space-between;padding: 0 20px;}
		.login .links .sns-login {display:flex; align-items:center; justify-content:center; gap:10px;}
		.login .links a {color: #fff;text-decoration: none;}
		.login .links .kakaoicon {display:block;width:50px;height:50px;border:none;border-radius:50%;background:#ffe90a url('https://upload.wikimedia.org/wikipedia/commons/thumb/e/e3/KakaoTalk_logo.svg/960px-KakaoTalk_logo.svg.png') no-repeat 50% / 80% auto;}
		.login .links .googleicon {display:block;width:50px;height:50px;border:none;border-radius:50%;background:#fff url('https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Google_%22G%22_logo.svg/1024px-Google_%22G%22_logo.svg.png') no-repeat 50% / 80% auto;}
		.login .links .signup {}
	</style>
</head>
<body>
<div class="ring">
	<i style="--clr:#00ff0a;"></i>
	<i style="--clr:#ff0057;"></i>
	<i style="--clr:#fffd44;"></i>
	<form method="post" action="loginForm.member" class="login">
		<h2>Login</h2>
		<input type="hidden" name="action" value="login">
		<div class="inputBx">
			<input type="text" name="userid" placeholder="user ID">
		</div>
		<div class="inputBx">
			<input type="password" name="userpw" placeholder="Password">
		</div>
		<div class="inputBx">
			<button type="submit">Sign in</button>
		</div>
		<div class="links">
			<div class="sns-login">
				<button onclick="kakaoLogin()" class="kakaoicon" aria-label="카카오 간편가입"></button>
				<a href="${pageContext.request.contextPath}/login/google" class="googleicon" aria-label="구글 간편가입"></a>
			</div>
			<a href="join.member" class="signup">Sign up</a>
		</div>
	</form>
</div>
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
