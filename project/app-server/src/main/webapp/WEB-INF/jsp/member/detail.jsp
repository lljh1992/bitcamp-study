<%@ page
        language="java"
        pageEncoding="UTF-8"
        contentType="text/html;charset=UTF-8"
        trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset='UTF-8'>
    <title>입주자</title>
    <style>
        table {
            width: 30%;
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
            text-align: center;
        }
        .footer {
            flex-shrink: 0;
        }

    </style>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>입주자</h1>

<c:if test="${empty member}">
    <p>해당 번호의 회원이 없습니다!</p>
</c:if>

<c:if test="${not empty member}">
    <form action='/member/update' method='post' enctype="multipart/form-data">
        <table border='1'>
            <tr>
                <th style='width:120px;'>사진</th>
                <td style='width:300px;'>
                    <c:if test="${empty member.photo}">
                        <img style='height:80px' src='/images/avatar.png'>
                    </c:if>
                    <c:if test="${not empty member.photo}">
                        <a href='https://kr.object.ncloudstorage.com/bitcamp-nc7-bucket-16/member2/${member.photo}'>
                            <img src='http://guosqxeocfoi19010728.cdn.ntruss.com/member2/${member.photo}?type=f&w=30&h=40&faceopt=true&ttype=jpg'>
                        </a>
                    </c:if>
                    <input type='file' name='photo'></td></tr>
            <tr>
                <th>이름</th>
                <td><input type='text' name='name' value='${member.name}'></td></tr>
            <tr>
                <th>핸드폰번호</th>
                <td><input type='phonenumber' name='phonenumber' value='${member.phonenumber}'></td></tr>
            <tr>
                <th>차량 번호</th>
                <td><input type='carnumber' name='carnumber' value='${member.carnumber}'></td></tr>
            <tr>
                <th>동-호수</th>
                <td><input type='building' name='building' value='${member.building}'></td></tr>
            <tr>
                <th>암호</th>
                <td><input type='password' name='password'></td></tr>
        </table>
        <div>
            <button>변경</button>
            <button type='reset'>초기화</button>
            <a href='/member/delete?building=${member.building}'>삭제</a>
            <a href='/member/list'>목록</a>
        </div>
    </form>
</c:if>

<jsp:include page="../footer.jsp"/>

</body>
</html>