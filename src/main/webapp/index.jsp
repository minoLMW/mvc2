<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
  <style>
		@import url("https://fonts.googleapis.com/css2?family=Quicksand:wght@300&display=swap");
		* {margin:0;padding:0;box-sizing:border-box;font-family:"Quicksand", sans-serif;}
		body {display:flex;justify-content:center;align-items:center;min-height:100vh;background:#111;width:100%;overflow:hidden;}
		.ring {position:relative;width:500px;height:500px;display:flex;justify-content:center;align-items:center; opacity:0; animation:opopcity 3s linear forwards;}
		.ring i {position:absolute;inset:0;border:2px solid #fff;transition:0.5s; z-index:-1;}
		.ring i:nth-child(1) {border-radius:38% 62% 63% 37% / 41% 44% 56% 59%;animation:animate 6s linear infinite;}
		.ring i:nth-child(2) {border-radius:41% 44% 56% 59%/38% 62% 63% 37%;animation:animate 4s linear infinite;}
		.ring i:nth-child(3) {border-radius:41% 44% 56% 59%/38% 62% 63% 37%;animation:animate2 10s linear infinite;}
		.ring i {border:6px solid var(--clr);filter:drop-shadow(0 0 20px var(--clr));}
		@keyframes opopcity {
			0% {opacity:0;}
			100% {opacity:1;}
		}
		@keyframes animate {
			0% {transform:rotate(0deg);}
			100% {transform:rotate(360deg);}
		}
		@keyframes animate2 {
			0% {transform:rotate(360deg);}
			100% {transform:rotate(0deg);}
		}
        .intro-text {position:absolute; right:300%; top:50%; transform:translate(50%, -50%); animation:showingTxt 3s linear forwards;}
		@keyframes showingTxt {
			0% {right:200%;}
			100% {right:50%;}
		}
        .intro-text h1 {font-size:30px; color:#fff; text-wrap:nowrap;}
        .intro-text a {font-size:24px; color:#fff; text-wrap:nowrap;}
	</style>
</head>
<body>
<div class="ring">
	<i style="--clr:#00ff0a;"></i>
	<i style="--clr:#ff0057;"></i>
	<i style="--clr:#fffd44;"></i>
</div>
<div class="intro-text">
    <h1><%= "Hello World!" %></h1>
    <a href="login.member">Hello Servlet</a>
</div>
</body>
</html>