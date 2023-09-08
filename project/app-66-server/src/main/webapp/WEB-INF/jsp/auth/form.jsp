<%@ page
        language="java"
        pageEncoding="UTF-8"
        contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='UTF-8'>
    <title>아파트 주차 관리 시스템</title>
    <style>
        body {
            text-align: center;
        }
        form {
            display: inline-block;
            text-align: left;
            border: 1px solid #ccc;
            padding: 20px;
            margin: 0 auto;
        }
    </style>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>로그인</h1>

<form action='login' method='post'>
    <table>
        <tr>
            <th>핸드폰번호</th>
            <td><input type='text' name='phonenumber' value='${cookie.phonenumber.value}'></td>
        </tr>
        <tr>
            <th>패스워드</th>
            <td><input type='password' name='password'></td>
        </tr>
        <tr>
            <td colspan='2'><input type='checkbox' name='savePhonenumber' ${cookie.phonenumber != null ? "checked" : ""}> 아이디 저장 <button>로그인</button></td>
        </tr>
    </table>
    <br>

</form>

<jsp:include page="../footer.jsp"/>

</body>
</html>
