<%@ page import="project.vo.Member" %>
<%@ page
        language="java"
        pageEncoding="UTF-8"
        contentType="text/html;charset=UTF-8"
        trimDirectiveWhitespaces="true"
        errorPage="/error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="memberDao" type="project.dao.MemberDao" scope="application"/>
<c:set var="refresh" value="2;url=/auth/form.jsp" scope="request"/>
<jsp:useBean id="m" class="project.vo.Member" scope="page"/>
<c:set target="${pageScope.m}" property="phonenumber" value="${param.phonenumber}"/>
<c:set target="${pageScope.m}" property="password" value="${param.password}"/>

<c:if test="${not empty param.savePhonenumber}">
    <%
        Cookie cookie = new Cookie("phonenumber", m.getPhonenumber());
        response.addCookie(cookie);
    %>
</c:if>

<c:if test="${empty param.savePhonenumber}">
    <%
        Cookie cookie = new Cookie("phonenumber", "no");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    %>
</c:if>


<c:set var="loginUser" value="${memberDao.findByPhonenumberAndPassword(m)}" scope="session"/>
<jsp:useBean id="loginUser" type="project.vo.Member" scope="session"/>
<c:redirect url="/"/>
