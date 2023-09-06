
<%@ page
        language="java"
        pageEncoding="UTF-8"
        contentType="text/html;charset=UTF-8"
        trimDirectiveWhitespaces="true"
        errorPage="/error.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="project.vo.Member" %>
<%@ page import="java.sql.Timestamp" %>

<jsp:useBean id="memberDao" type="project.dao.MemberDao" scope="application"/>
<jsp:useBean id="parkingTimeDao" type="project.dao.ParkingTimeDao" scope="application"/>
<jsp:useBean id="sqlSessionFactory" type="org.apache.ibatis.session.SqlSessionFactory" scope="application"/>

<c:set var="member" value="${memberDao.findByCar(param.carnumber)}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset='UTF-8'>
    <title>아파트 주차 관리 시스템</title>
    <meta http-equiv='refresh' content='1;url=/member/list.jsp'>
</head>
<body>


<%
String carnumber = request.getParameter("carnumber"); // 클라이언트에서 전송한 차량 번호 데이터
Timestamp entryTime = new Timestamp(System.currentTimeMillis()); // 현재 시간을 입차 시간으로 사용


// 차량 번호로 회원 정보 조회
Member member = memberDao.findByCar(carnumber);
%>
<%
<c:if
if (member == null) {
<p>이 번호로 등록된 차량이 없습니다!</p>
} else {

request.getRequestDispatcher("/header.jsp").include(request, response);

<h1>입차</h1>

ParkingTime pt = new ParkingTime();
pt.setCarnumber(carnumber);
pt.setEntryTime(entryTime);// 멤버의 입차 시간을 저장합니다.

try {
// MySQLParkingTimeDao parkingTimeDao = new MySQLParkingTimeDao(sqlSessionFactory);
parkingTimeDao.saveEntry(pt);
sqlSessionFactory.openSession(false).commit();

<p>등록 성공입니다!</p>
<p>입차 시간: " + entryTime + "</p>

} catch (Exception e) {
sqlSessionFactory.openSession(false).rollback();
<p>등록 실패입니다!</p>
e.printStackTrace();
}

request.getRequestDispatcher("/footer.jsp").include(request, response);
%>
</body>
</html>