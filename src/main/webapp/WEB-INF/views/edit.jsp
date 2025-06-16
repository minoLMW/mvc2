<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글 수정</title>
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
        .title {margin-bottom:20px; color:#000}
        .title input {width:100%; padding:12px; border:1px solid #e6e6e6; border-radius:12px; font-size:20px;}
        .content {color:#000;}
        .button {margin-top:20px; text-align:center;}
        .button button {width:160px; height:50px; border-radius:12px; font-size:20px; border:none; background-color:#cdb4db;}
        .note-editor {height:500px; background-color:#fff;}

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
    </style>
</head>
<body class="snowing">
    <h2>게시글 수정</h2>
    <form method="post" action="${pageContext.request.contextPath}/edit.board">
        <input type="hidden" name="board_idx" value="${board.board_idx}">
        <div class="title">
            <label for="title">제목</label>
            <input type="text" id="title" name="title" value="${board.title}" required />
        </div>
        <div class="content">
            <label for="content">내용</label>
            <textarea id="content" name="content" required>${board.content}</textarea>
        </div>
        <div class="button">
            <button type="submit">수정 완료</button>
        </div>
    </form>
</body>
</html>
