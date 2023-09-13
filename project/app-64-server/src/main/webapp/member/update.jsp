<%@ page
        language="java"
        pageEncoding="UTF-8"
        contentType="text/html;charset=UTF-8"
        trimDirectiveWhitespaces="true"
        errorPage="/error.jsp" %>
<%@ page import="project.vo.Member" %>

<jsp:useBean id="memberDao" type="project.dao.MemberDao" scope="application"/>
<jsp:useBean id="sqlSessionFactory" type="org.apache.ibatis.session.SqlSessionFactory" scope="application"/>
<jsp:useBean id="ncpObjectStorageService" type="project.service.NcpObjectStorageService" scope="application"/>


<%

    Member member = new Member();
    member.setBuilding(request.getParameter("building"));
    member.setName(request.getParameter("name"));
    member.setPhonenumber(request.getParameter("phonenumber"));
    member.setPassword(request.getParameter("password"));
    member.setCarnumber(request.getParameter("carnumber"));

    Part photoPart = request.getPart("photo");
    if (photoPart.getSize() > 0) {
        String uploadFileUrl = ncpObjectStorageService.uploadFile("bitcamp-nc7-bucket-16",
                "member2/", photoPart);
        member.setPhoto(uploadFileUrl);
    }

    if (memberDao.update(member) == 0) {
        throw new Exception("회원이 없습니다.");
    } else {
        sqlSessionFactory.openSession(false).commit();
        response.sendRedirect("list.jsp");
    }

%>