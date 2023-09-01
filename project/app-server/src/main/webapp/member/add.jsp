<%@ page
        language="java"
        pageEncoding="UTF-8"
        contentType="text/html;charset=UTF-8"
        trimDirectiveWhitespaces="true"
        errorPage="/error.jsp"%>
<%@ page import="project.vo.Member"%>

<jsp:useBean id="memberDao" type="project.dao.MemberDao" scope="application"/>
<jsp:useBean id="sqlSessionFactory" type="org.apache.ibatis.session.SqlSessionFactory" scope="application"/>
<jsp:useBean id="ncpObjectStorageService" type="project.util.NcpObjectStorageService" scope="application"/>

<%
  request.setAttribute("refresh", "2;url=list.jsp");

Member m = new Member();
m.setBuilding(request.getParameter("building"));
m.setName(request.getParameter("name"));
m.setPhonenumber(request.getParameter("phonenumber"));
m.setPassword(request.getParameter("password"));
m.setCarnumber(request.getParameter("carnumber"));

Part photoPart = request.getPart("photo");
if (photoPart.getSize() > 0) {
String uploadFileUrl = ncpObjectStorageService.uploadFile("bitcamp-nc7-bucket-16",
"member2/", photoPart);
m.setPhoto(uploadFileUrl);
}

memberDao.insert(m);
memberDao.insertCar(m);
sqlSessionFactory.openSession(false).commit();
response.sendRedirect("list.jsp");

%>