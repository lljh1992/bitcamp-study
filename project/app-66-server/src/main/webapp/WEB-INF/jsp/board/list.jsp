<%@ page
        language="java"
        pageEncoding="UTF-8"
        contentType="text/html;charset=UTF-8"
        trimDirectiveWhitespaces="true"
        errorPage="../error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>게시글</title>
  <style>
    table {
      width: 80%;
      margin: 20px auto;
      background-color: #fff;
      border: 1px solid #ccc;
    }
    th, td {
      padding: 10px;
      text-align: center;
      border: 1px solid #ccc;
    }
    th {
      background-color: #007bff;
      color: #fff;
      white-space: nowrap; /* 텍스트 줄 바꿈 방지 */
    }
    tr:nth-child(even) {
      background-color: #f2f2f2;
    }
    h1 {
      text-align: center;
    }
    div {
      text-align: center;
    }
    .table-container {
      overflow-x: auto;
      max-width: 100%;
      max-height: 70vh;
    }
    body {
      display: flex;
      flex-direction: column;
      min-height: 100vh;
    }
    .footer {
      flex-shrink: 0;
    }
  </style>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>게시글 목록 <a href='/'>[ < ]</a></h1>
<div style='margin:5px;'>
<a href='add?category=${param.category}'>새 글</a>
</div>
<div class="table-container">
<table border='1'>
<thead>
<tr><th>번호</th> <th>제목</th> <th>작성자</th> <th>조회수</th> <th>등록일</th></tr>
</thead>
  
<tbody>
<c:forEach items="${list}" var="board">
<tr>
  <td>${board.no}</td>
  <td><a href='detail?category=${board.category}&no=${board.no}'>
    ${board.title.length() > 0 ? board.title : "제목없음"}
  </a>
  </td>
  <td>${board.writer.name}</td>
  <td>${board.viewCount}</td>
  <td><fmt:formatDate value="${board.createdDate}" pattern="yyyy-MM-dd"/></td>
</tr>
</c:forEach>
</tbody>
</table>

<jsp:include page="../footer.jsp"/>

</body>
</html>
