<%@ page
        language="java"
        pageEncoding="UTF-8"
        contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='UTF-8'>
    <title>아파트 주차 관리 시스템</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>로그인</h1>

<form action='/auth/login.jsp' method='post'>
    <table border='1'>
        <tr>
            <th>핸드폰번호</th>
            <td><input type='phonenumber' name='phonenumber' value='${cookie.phonenumber.value}'></td>
        </tr>
        <tr>
            <th>패스워드</th>
            <td><input type='password' name='password'></td>
        </tr>
    </table>
    <button>로그인</button>
    <input type='checkbox' name='savePhonenumber' ${cookie.phonenumber != null ? "checked" : ""}> 아이디 저장
</form>

<jsp:include page="../footer.jsp"/>

</body>
</html>


