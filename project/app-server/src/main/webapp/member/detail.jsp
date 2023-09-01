<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="member" value="${memberDao.findBy(param.building)}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset='UTF-8'>
    <title>입주자</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>입주자</h1>

<c:choose>
    <c:when test="${empty member}">
        <p>해당 번호의 회원이 없습니다!</p>
    </c:when>
    <c:otherwise>
        <form action='/member/update.jsp' method='post'>
            <table border='1'>
                <tr>
                    <th style='width:120px;'>사진</th>
                    <td style='width:300px;'>
                        <c:choose>
                            <c:when test="${empty member.photo}">
                                <img style='height:80px' src='/images/avatar.png'>
                            </c:when>
                            <c:otherwise>
                                <a href='https://kr.object.ncloudstorage.com/bitcamp-nc7-bucket-16/member2/${member.photo}'>
                                    <img src='http://guosqxeocfoi19010728.cdn.ntruss.com/member2/${member.photo}?type=f&w=30&h=40&faceopt=true&ttype=jpg'>
                                </a>
                            </c:otherwise>
                        </c:choose>
                        <input type='file' name='photo'>
                    </td>
                </tr>
                <tr>
                    <th>이름</th>
                    <td><input type='text' name='name' value='${member.name}'></td>
                </tr>
                <tr>
                    <th>핸드폰번호</th>
                    <td><input type='phonenumber' name='phonenumber' value='${member.phonenumber}'></td>
                </tr>
                <tr>
                    <th>차량 번호</th>
                    <td><input type='carnumber' name='carnumber' value='${member.carnumber}'></td>
                </tr>
                <tr>
                    <th>동-호수</th>
                    <td><input type='building' name='building' value='${member.building}'></td>
                </tr>
                <tr>
                    <th>암호</th>
                    <td><input type='password' name='password'></td>
                </tr>
            </table>
            <div>
                <button>변경</button>
                <button type='reset'>초기화</button>
                <a href='/member/delete.jsp?building=${member.building}'>삭제</a>
                <a href='/member/list.jsp'>목록</a>
            </div>
        </form>
    </c:otherwise>
</c:choose>

<jsp:include page="../footer.jsp"/>

</body>
</html>
