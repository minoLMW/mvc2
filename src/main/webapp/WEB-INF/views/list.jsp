<%--
  Created by IntelliJ IDEA.
  User: minwo
  Date: 2025-06-13
  Time: 오후 4:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>게시글 목록</title>
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
      table {width:calc(100% - 50px); margin:0 auto; table-layout:fixed; border:none;border-collapse: collapse; border-spacing: 0;}
      table thead {background-color:#001b3b;}
      table thead th {padding:12px; border-top:1px solid #666;}
      table tbody td {padding:12px; border-top:1px solid #666;}
      table tbody tr td a {font-family:'맑은 고딕'; color:#fff}
      table tbody tr:last-child td {border-bottom:1px solid #666;}
      table tbody tr:hover {background-color:#14a5a5;}
      table tbody tr td {position:relative;}
      table tbody tr td::before {content:''; position:absolute; left:0; top:50%; transform:translateY(-50%); width:1px; height:16px; background-color:#e6e6e6;}
      table tbody tr td:first-child::before {display:none;}
      table tbody tr td[colspan="6"]:hover {background-color:none;}
      .num {text-align:center;}
      .title {overflow: hidden; text-overflow: ellipsis; white-space: nowrap; text-align:center;}
      .title a {overflow: hidden;text-overflow: ellipsis;white-space: nowrap;text-decoration:underline;}
      .writer {text-align:center;}
      .createdAt {text-align:center;}
      .views {text-align:center;}
      .write-btn {margin-bottom:20px; text-align:right;}
      .write-btn a {display:inline-block; height:50px; padding:0 16px; font-size:20px; border-radius:12px; border:none; color:#fff; background-color:#cdb4db}

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
    <h2>게시글 목록</h2>
    <div class="container">
      <div class="write-btn">
        <a href="create.board">글쓰기</a>
      </div>
      <table>
        <colgroup>
          <col class="num">
          <col>
          <col class="writer">
          <col class="createdAt">
          <col class="views">
          <col class="actions">
        </colgroup>
        <thead>
          <tr>
            <th class="num">번호</th>
            <th class="title">제목</th>
            <th class="writer">작성자</th>
            <th class="createdAt">작성일</th>
            <th class="views">조회수</th>
            <th class="actions">관리</th>
          </tr>
        </thead>
        <tbody>
          <c:if test="${empty list}">
            <tr>
              <td colspan="6" style="text-align: center;">등록된 게시글이 없습니다.</td>
            </tr>
          </c:if>
          <c:forEach items="${list}" var="board">
            <tr>
              <td class="num">${board.board_idx}</td>
              <td class="title"><a href="detail.board?board_idx=${board.board_idx}">${board.title}</a></td>
              <td class="writer">${board.nickname}</td>
              <td class="createdAt">${board.regdate}</td>
              <td class="views">${board.view_count}</td>
              <td class="actions">
                <c:if test="${sessionScope.user.userid eq board.userid}">
                  <a href="edit.board?board_idx=${board.board_idx}">수정</a>
                  <a href="removeForm.board?board_idx=${board.board_idx}">삭제</a>
                </c:if>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </body>
</html>
