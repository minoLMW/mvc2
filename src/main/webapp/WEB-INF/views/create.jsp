<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2025-06-13
  Time: 오후 2:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>새 글쓰기</title>
  <style>
    * {margin:0; padding:0;}
    body {padding:0 25px; background-color:#001b3b; color:#fff;}
    h2 {display:flex; justify-content:center; align-items:center; padding:25px 50px 25px 25px; font-size:50px;}
    h2::after {content:''; display:inline-block; width:75px; height:75px;  background:url('https://www.notion.so/_assets/a7e5ea83ae6a4462.gif') no-repeat center right / 75px auto;;}
    form {max-width:500px; margin:0 auto;}
    .title {margin-bottom:20px;}
    .title input {width:100%; padding:12px; border:1px solid #E6E6E6; border-radius:12px; font-size:20px;}
    .content {margin-bottom:20px;}
    .content textarea {width:100%; height:300px; padding:12px; border:1px solid #E6E6E6; border-radius:12px; font-size:20px; resize:none;}
    .button {margin-top:20px; text-align:center;}
    .button button {width:160px; height:50px; border-radius:12px; font-size:20px; border:none; background-color:#cdb4db; color:#fff;}
    /* 눈 내리는 배경 클래스 */
    .snowing {
      background-image:
              url('https://currys-ssl.cdn.dixons.com/css/themes/email/2017-2018/wk35/XmasDayPC/V1/_snow.png'),
              url('https://currys-ssl.cdn.dixons.com/css/themes/email/2017-2018/wk35/XmasDayPC/V1/_snow2.png'),
              url('https://currys-ssl.cdn.dixons.com/css/themes/email/2017-2018/wk35/XmasDayPC/V1/_snow3.png');
      background-repeat: repeat;
      background-attachment: fixed;
      animation: snow 20s steps(600) infinite;
    }
    /* 눈 내리는 애니메이션 */
    @keyframes snow {
      0% {
        background-position: 0em 0em, 0em 0em, 0em 0em;
      }
      100% {
        background-position: -50em 200em, 0em 50em, 50em 100em;
      }
    }
  </style>
</head>
<body class="snowing">
<h2>게시글 작성</h2>
<form method="post" action="createForm.board">
  <div class="title">
    <label for="title">제목</label>
    <input type="text" id="title" name="title" placeholder="제목을 입력하세요" required />
  </div>
  <div class="content">
    <label for="content">내용</label>
    <textarea id="content" name="content" placeholder="내용을 입력하세요" required></textarea>
  </div>
  <div class="button">
    <button type="submit">작성 완료</button>
  </div>
</form>

</body>
</html>