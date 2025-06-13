<%--
  Created by IntelliJ IDEA.
  User: minwo
  Date: 2025-06-13
  Time: 오후 4:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <style>
    * {margin:0; padding:0;}
    h2 {display:flex; align-items:center; padding:25px 50px 25px 25px;}
    h2::after {content:''; display:inline-block; width:50px; height:50px;  background:url('https://www.notion.so/_assets/a7e5ea83ae6a4462.gif') no-repeat center right / 50px auto;;}
    h2 img {width:50px;}
    table {width:calc(100% - 50px); margin:0 auto; table-layout:fixed; border:none;border-collapse: collapse; border-spacing: 0;}
    table thead {background-color:#f6f6f6;}
    table thead th {padding:12px; border-top:1px solid #E6E6E6;}
    table tbody td {padding:12px; border-top:1px solid #E6E6E6;}
    table tbody tr:last-child td {border-bottom:1px solid #E6E6E6;}
    table tbody tr:hover {background-color:#FFE5B4;}
    table tbody tr td {position:relative;}
    table tbody tr td::before {content:''; position:absolute; left:0; top:50%; transform:translateY(-50%); width:1px; height:16px; background-color:#e6e6e6;}
    table tbody tr td:first-child::before {display:none;}
    .num {text-align:center;}
    .title {overflow: hidden; text-overflow: ellipsis; white-space: nowrap; text-align:center;}
    .title a {overflow: hidden;text-overflow: ellipsis;white-space: nowrap;text-decoration:underline;}
    .writer {text-align:center;}
    .createdAt {text-align:center;}
    .views {text-align:center;}
  </style>
  <body>
  <h2>게시글 목록</h2>
  <table>
    <colgroup>
      <col style="width:80px;">
      <col>
      <col style="width:100px;">
      <col style="width:120px;">
      <col style="width:100px;">
    </colgroup>
    <thead>
    <tr>
      <th class="num">번호</th>
      <th class="title">제목</th>
      <th class="writer">작성자</th>
      <th class="createdAt">작성일</th>
      <th class="views">조회수</th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td class="num">0</td>
      <td class="title"><a href="detail?id=${post.id}"><!-- ${post.title} -->fgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajip</a></td>
      <td class="writer"><!-- ${post.writer} -->이민우</td>
      <td class="createdAt"><!-- ${post.createdAt} -->2025-06-13</td>
      <td class="views"><!-- ${post.views} -->999</td>
    </tr>
    <tr>
      <td class="num">0</td>
      <td class="title"><a href="detail?id=${post.id}"><!-- ${post.title} -->fgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajip</a></td>
      <td class="writer"><!-- ${post.writer} -->이민우</td>
      <td class="createdAt"><!-- ${post.createdAt} -->2025-06-13</td>
      <td class="views"><!-- ${post.views} -->999</td>
    </tr>
    <tr>
      <td class="num">0</td>
      <td class="title"><a href="detail?id=${post.id}"><!-- ${post.title} -->fgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajipfgewragrjeapgireajip</a></td>
      <td class="writer"><!-- ${post.writer} -->이민우</td>
      <td class="createdAt"><!-- ${post.createdAt} -->2025-06-13</td>
      <td class="views"><!-- ${post.views} -->999</td>
    </tr>
    </tbody>
  </table>
  <script>
    document.addEventListener("DOMContentLoaded", function () {
      const rows = document.querySelectorAll("tbody tr");
      rows.forEach((row, index) => {
        const numCell = row.querySelector(".num");
        if (numCell) {
          numCell.textContent = index + 1;
        }
      });
    });
  </script>
  </body>
  <script>
    document.addEventListener("DOMContentLoaded", function () {
      const rows = document.querySelectorAll("tbody tr");
      rows.forEach((row, index) => {
        const numCell = row.querySelector(".num");
        if (numCell) {
          numCell.textContent = index + 1;
        }
      });
    });
  </script>
</html>
