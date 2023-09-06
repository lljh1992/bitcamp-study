<%@ page
        language="java"
        pageEncoding="UTF-8"
        contentType="text/html;charset=UTF-8" %> <%-- directive element --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<h1>입주자 목록 <a href='/'>[ < ]</a></h1>
<div style='margin:5px;'>
    <a href='/member/add'>입주자 등록</a>
    <a href='/member/entry.html'>입차</a>
    <a href='/member/exit.html'>출차</a>
    <a href='/member/detailcar'>입출차 기록</a>
</div>
<div class="table-container">
<table border='1'>
    <thead>
    <tr>
        <th>동-호수</th>
        <th>이름</th>
        <th>H.P</th>
        <th>차량 번호</th>
    </tr>
    </thead>

    <c:forEach items="${list}" var="member">
        <tr>
            <td><img
                    src='http://guosqxeocfoi19010728.cdn.ntruss.com/member2/${member.photo}?type=f&w=30&h=40&faceopt=true&ttype=jpg'>
                <a href='/member/detail?building=${member.building}'>${member.building}
                </a>
            </td>
            <td>${member.name}</td>
            <td>${member.phonenumber}</td>
            <td>${member.carnumber}</td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</div>


<jsp:include page="../footer.jsp"/>

</body>
</html>