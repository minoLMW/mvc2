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
          src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonnums_2106@1.1/SF_HambakSnow.woff') format('woff');
          font-weight: normal;
          font-style: normal;
      }
      h2 {display:flex; align-items:center; padding:25px 50px 25px 25px;}
      h2::after {content:''; display:inline-block; width:50px; height:50px;  background:url('https://www.notion.so/_assets/a7e5ea83ae6a4462.gif') no-repeat center right / 50px auto;;}
      .container {width: calc(100% - 50px); margin: 0 auto;}
      .write-btn {text-align: right; margin-bottom: 20px;}
      .write-btn a {display: inline-block; padding: 10px 20px; background-color: #cdb4db; color: white; text-decoration: none; border-radius: 5px;}
      table {width: 100%; table-layout: fixed; border: none; border-collapse: collapse; border-spacing: 0;}
      table thead {background-color: #f6f6f6;}
      table thead th {padding: 12px; border-top: 1px solid #E6E6E6;}
      table tbody td {padding: 12px; border-top: 1px solid #E6E6E6;}
      table tbody tr:last-child td {border-bottom: 1px solid #E6E6E6;}
      table tbody tr:hover {background-color: #FFE5B4;}
      table tbody tr td {position: relative;}
      table tbody tr td::before {content: ''; position: absolute; left: 0; top: 50%; transform: translateY(-50%); width: 1px; height: 16px; background-color: #e6e6e6;}
      table tbody tr td:first-child::before {display: none;}
      .num {text-align: center; width: 80px;}
      .title {overflow: hidden; text-overflow: ellipsis; white-space: nowrap; text-align: center;}
      .title a {overflow: hidden; text-overflow: ellipsis; white-space: nowrap; text-decoration: underline;}
      .writer {text-align: center; width: 100px;}
      .createdAt {text-align: center; width: 120px;}
      .views {text-align: center; width: 100px;}
      .actions {text-align: center; width: 120px;}
      .actions a {margin: 0 5px; color: #666; text-decoration: none;}
      .actions a:hover {color: #cdb4db;}
    </style>
  </head>
  <body>
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
