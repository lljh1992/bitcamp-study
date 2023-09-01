<%@ page
        language="java"
        pageEncoding="UTF-8"
        contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>아파트 주차관리 시스템</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>아파트 주차관리 시스템</h1>
<form action='/member/add.jsp' method='post' enctype='multipart/form-data'>
<table border='1'>
<tr>
<th>동-호수</th> <td style='width:200px;'><input type='building' name='building'></td>
</tr>
<tr>
 <th>이름</th> <td><input type='text' name='name'></td>
</tr>
<tr>
 <th>H.P</th> <td><input type='phonenumber' name='phonenumber'></td>
</tr>
<tr>
 <th>암호</th> <td><input type='password' name='password'></td>
</tr>
<tr>
 <th>차량번호</th> <td><input type='carnumber' name='carnumber'></td>
</tr>
<tr>
 <th>사진</th> <td><input type='file' name='photo'></td>
</tr>
</table>
<button>등록</button>
</form>

<jsp:include page="../footer.jsp"/>

</body>
</html>
