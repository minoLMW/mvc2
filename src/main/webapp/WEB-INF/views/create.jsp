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
  <link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/summernote-lite.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/summernote-emoji@0.8.1/summernote-emoji.css" rel="stylesheet">
  <style>
    * {margin:0; padding:0; font-family:'SF_HambakSnow';}
    @font-face {
      font-family: 'SF_HambakSnow';
      src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_2106@1.1/SF_HambakSnow.woff') format('woff');
      font-weight: normal;
      font-style: normal;
    }
    body {padding:0 25px; background-color:#001b3b; color:#fff;}
    h2 {display:flex; justify-content:center; align-items:center; padding:25px 50px 25px 25px; font-size:50px; font-family: 'SF_HambakSnow';}
    h2::after {content:''; display:inline-block; width:75px; height:75px;  background:url('https://www.notion.so/_assets/a7e5ea83ae6a4462.gif') no-repeat center right / 75px auto;;}
    forms {max-width:500px;}
    .title {margin-bottom:20px;}
    .title input {width:100%; padding:12px; border:1px solid #E6E6E6; border-radius:12px; font-size:20px;}
    .button {margin-top:20px; text-align:center;}
    .button button {width:160px; height:50px; border-radius:12px; font-size:20px; border:none; background-color:#cdb4db; color:fff}
    .note-editor.note-frame {background-color:#fff;}
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
<form method="post" action="createForm.member" class="forms">
  <div class="title">
    <input type="text" name="title" placeholder="제목을 입력하세요" required />
  </div>
  <div class="edit-box">
    <textarea id="summernote" name="editordata"></textarea>
  </div>
  <div class="button">
    <button type="submit">작성 완료</button>
  </div>
</form>
<script src="https://cdn.jsdelivr.net/npm/summernote-emoji@0.8.1/summernote-emoji.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/summernote-lite.min.js"></script>
<script>
  $(document).ready(function () {
    $('#summernote').summernote({
      height: 300,
      toolbar: [
        ['style', ['bold', 'italic', 'underline']],
        ['insert', ['emoji', 'link', 'picture']],
        ['para', ['ul', 'ol', 'paragraph']],
        ['view', ['codeview']]
      ]
    });
  });
</script>
</body>
</html>