<%@ page
        language="java"
        pageEncoding="UTF-8"
        contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset='UTF-8'>
    <title>아파트 주차관리 시스템</title>

    <style>
        table {
            width: 20%;
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
            color: #020000;
            white-space: nowrap; /* 텍스트 줄 바꿈 방지 */
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        h1 {
            text-align: center;
            margin: 50px;
        }
        div {
            text-align: center;
        }
        form {
            text-align: center;
        }

    </style>

</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>아파트 주차관리 시스템</h1>
<div style='margin:50px;'></div>
<form action='/member/add.jsp' method='post' enctype='multipart/form-data'>
    <table border='1'>
        <tr>
            <th>동-호수</th>
            <td style='width:200px;'><input type='building' name='building'></td>
        </tr>
        <tr>
            <th>이름</th>
            <td><input type='text' name='name'></td>
        </tr>
        <tr>
            <th>H.P</th>
            <td><input type='phonenumber' name='phonenumber'></td>
        </tr>
        <tr>
            <th>암호</th>
            <td><input type='password' name='password'></td>
        </tr>
        <tr>
            <th>차량번호</th>
            <td><input type='carnumber' name='carnumber'></td>
        </tr>
        <tr>
            <th>사진</th>
            <td><input type='file' name='photo'></td>
        </tr>
    </table>
    <button>등록</button>
</form>

<jsp:include page="../footer.jsp"/>

</body>
</html>
