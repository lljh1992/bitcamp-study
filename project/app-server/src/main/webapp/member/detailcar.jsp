<%@ page
        language="java"
        pageEncoding="UTF-8"
        contentType="text/html;charset=UTF-8"
        trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="memberDao" type="project.dao.MemberDao" scope="application"/>
<jsp:useBean id="parkingTimeDao" type="project.dao.ParkingTimeDao" scope="application"/>
<jsp:useBean id="sqlSessionFactory" type="org.apache.ibatis.session.SqlSessionFactory" scope="application"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset='UTF-8'>
    <title>입주자</title>
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

<h1>입주자 목록 <a href='/'>[ < ]</a></h1>

<div style='margin:5px;'></div>
<div class="table-container">

<table border='1'>
    <thead>
    <tr>
        <th>차량번호</th>
        <th>입차</th>
        <th>출차</th>
    </tr>
    </thead>
    <tbody>

    <c:set var="list" value="${parkingTimeDao.findinout()}" scope="page"/>
    <c:forEach items="${list}" var="p">
        <td>${p.getCarnumber()}</td>
        <td>
        <c:choose>
            <c:when test="${not empty p.entryTime}">
                ${p.entryTime}
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>
        </td>
        <td>
        <c:choose>
            <c:when test="${not empty p.exitTime}">
                ${p.exitTime}
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>
        </td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</div>

<jsp:include page="../footer.jsp"/>

</body>
</html>
