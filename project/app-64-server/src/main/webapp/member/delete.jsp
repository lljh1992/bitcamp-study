<%@ page
        language="java"
        pageEncoding="UTF-8"
        contentType="text/html;charset=UTF-8"
        trimDirectiveWhitespaces="true"
        errorPage="/error.jsp" %>
<jsp:useBean id="memberDao" type="project.dao.MemberDao" scope="application"/>
<jsp:useBean id="sqlSessionFactory" type="org.apache.ibatis.session.SqlSessionFactory" scope="application"/>

<%
    request.setAttribute("refresh", "2;url=list.jsp");

    if (memberDao.delete(request.getParameter("building")) == 0) {
        throw new Exception("입주자가 없습니다.");
    } else {
        sqlSessionFactory.openSession(false).commit();
        response.sendRedirect("/member/list.jsp");
    }

%>