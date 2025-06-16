<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  com.koreait.mvc2.dto.MemberDTO user = (com.koreait.mvc2.dto.MemberDTO) session.getAttribute("user");

%>
<html>
<head>
  <title>회원정보 수정</title>
<style>
	@import url("https://fonts.googleapis.com/css2?family=Quicksand:wght@300&display=swap");
	* {margin:0;padding:0;box-sizing:border-box;font-family:"Quicksand", sans-serif;}
	body {display:flex;justify-content:center;align-items:center;min-height:100vh;background:#111;width:100%;overflow:hidden;}
	.ring {position:relative;width:500px;height:500px;display:flex;justify-content:center;align-items:center;}
	.ring i {position:absolute;inset:0;border:2px solid #fff;transition:0.5s; z-index:-1; opacity:0.3;}
	.ring i:nth-child(1) {border-radius:38% 62% 63% 37% / 41% 44% 56% 59%;animation:animate 6s linear infinite;}
	.ring i:nth-child(2) {border-radius:41% 44% 56% 59%/38% 62% 63% 37%;animation:animate 4s linear infinite;}
	.ring i:nth-child(3) {border-radius:41% 44% 56% 59%/38% 62% 63% 37%;animation:animate2 10s linear infinite;}
	.ring i {border:6px solid var(--clr);filter:drop-shadow(0 0 20px var(--clr));}
	@keyframes animate {
		0% {transform:rotate(0deg);}
		100% {transform:rotate(360deg);}
	}
	@keyframes animate2 {
		0% {transform:rotate(360deg);}
		100% {transform:rotate(0deg);}
	}
	.modify {position:absolute;width:640px;height:100%;display:flex; flex-wrap:wrap; justify-content:space-between;align-items:center;gap:10px; padding-bottom:80px;}
	.modify h2 {width:100%; font-size:2em;color:#fff; text-align:center;}
	.modify .inputBx {position:relative;width:calc(50% - 10px);margin-bottom:10px;;}
	.modify .inputBx input,
	.modify .inputBx select,
	.modify .inputBx button {position:relative;width:100%;padding:12px 20px;background:#111;border:2px solid #fff;border-radius:40px;font-size:19px;color:#fff;box-shadow:none;outline:none;}
	.modify .inputBx button[type="submit"] {width:100%;background:#0078ff;background:linear-gradient(45deg, #ff357a, #fff172);border:none;cursor:pointer;}
	.modify .inputBx input::placeholder {color:rgba(255, 255, 255, 0.75);}
	.modify .inputBx.w-full {display:flex; width:100%;}
	.modify .inputBx .txt {position:relative;width:100%;padding:12px 20px;background:#666;border:2px solid #fff;border-radius:40px;font-size:19px;color:#fff;box-shadow:none;outline:none;text-align:center;}
</style>
</head>
<body>
<div class="ring">
	<i style="--clr:#00ff0a;"></i>
	<i style="--clr:#ff0057;"></i>
	<i style="--clr:#fffd44;"></i>
  <form method="post" action="modifyForm.member" class="modify">
    <h2>Modify</h2>
		<div class="inputBx w-full">
      <p class="txt">아이디: ${user.userid}</p>
    </div>
		<div class="inputBx">
      <input type="text" name="name" value="${user.name}" placeholder="이름">
    </div>
		<div class="inputBx">
      <input type="text" name="hp" value="${user.hp}" placeholder="휴대폰">
    </div>
		<div class="inputBx">
      <input type="email" name="email" value="${user.email}" placeholder="이메일">
    </div>
		<div class="inputBx">
      <select name="gender">
        <option ${user.gender == "남자" ? "selected" : ""} value="남자">남자</option>
        <option ${user.gender == "여자" ? "selected" : ""} value="여자">여자</option>
      </select>
    </div>
		<div class="inputBx">
      <input type="text" name="zipcode" value="${user.zipcode}" placeholder="우편번호">
    </div>
		<div class="inputBx">
      <input type="text" name="address1" value="${user.address1}" placeholder="주소">
    </div>
		<div class="inputBx">
      <input type="text" name="address2" value="${user.address2}" placeholder="상세주소">
    </div>
		<div class="inputBx">
      <input type="text" name="address3" value="${user.address3}" placeholder="참고항목">
    </div>
		<div class="inputBx w-full">
			<button type="submit">수정 완료</button>
		</div>
  </form>
</div>
</body>
</html>
