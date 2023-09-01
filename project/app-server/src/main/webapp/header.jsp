<%@ page
        language="java"
        pageEncoding="UTF-8"
        contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style='background-color: #007bff; color: #fff; padding: 10px; text-align: center; white-space: nowrap;'>
    <img src='/images/logo.png' style='height:40px; vertical-align: middle; margin-right: 10px;'>
    <a href='/member/list.jsp' style='color: #fff; text-decoration: none; margin: 10px;'>아파트 주차관리 시스템</a>
    <span style='color: #fff; margin: 10px;'></span>
    <a href='/board/list.jsp?category=1' style='color: #fff; text-decoration: none; margin: 10px;'>민원사항</a>
    <span style='color: #fff; margin: 10px;'></span>
    <a href='/board/list.jsp?category=2' style='color: #fff; text-decoration: none; margin: 10px;'>공지사항</a>

    <c:choose>
        <c:when test="${empty sessionScope.loginUser}">
            <a href='/auth/form.jsp' style='color: #fff; text-decoration: none; margin: 10px;'>로그인</a>
        </c:when>
        <c:otherwise>
            <c:if test="${empty sessionScope.loginUser.photo}">
                <img style='height:40px; margin: 10px;' src='/images/avatar.png'>
            </c:if>
            <c:if test="${not empty sessionScope.loginUser.photo}">
                <img style='height:40px; margin: 10px;' src='http://guosqxeocfoi19010728.cdn.ntruss.com/member2/${loginUser.photo}?type=f&w=30&h=40&faceopt=true&ttype=jpg'>
            </c:if>
            <span style='color: #fff; margin: 10px;'>/</span>
            ${loginUser.name} <a href='/auth/logout.jsp' style='color: #fff; text-decoration: none; margin: 10px;'>로그아웃</a>
        </c:otherwise>
    </c:choose>
</div>






