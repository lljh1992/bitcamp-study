package project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;
import project.dao.MemberDao;
import project.dao.MySQLParkingTimeDao;
import project.dao.ParkingTimeDao;
import project.vo.Member;
import project.vo.ParkingTime;

@WebServlet("/member/entry")
public class MemberEntryController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    request.getRequestDispatcher("/WEB-INF/jsp/member/entry.html").include(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {


    String carnumber = request.getParameter("carnumber"); // 클라이언트에서 전송한 차량 번호 데이터
    Timestamp entryTime = new Timestamp(System.currentTimeMillis()); // 현재 시간을 입차 시간으로 사용

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
    ParkingTimeDao parkingTimeDao = new MySQLParkingTimeDao(sqlSessionFactory);
    MemberDao memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");

    // 차량 번호로 회원 정보 조회
    Member member = memberDao.findByCar(carnumber);

    if (member == null) {
      out.println("<p>이 번호로 등록된 차량이 없습니다!</p>");
    } else {
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>아파트 주차 관리 시스템</title>");
       out.println("<meta http-equiv='refresh' content='1;url=/member/list'>");
      out.println("</head>");
      out.println("<body>");


      out.println("<h1>입차</h1>");

      ParkingTime pt = new ParkingTime();
      pt.setCarnumber(carnumber);
      pt.setEntryTime(entryTime);

      try {
        parkingTimeDao.saveEntry(pt);
        sqlSessionFactory.openSession(false).commit();

        out.println("<p>등록 성공입니다!</p>");
        out.println("<p>입차 시간: " + entryTime + "</p>");

      } catch (Exception e) {
        sqlSessionFactory.openSession(false).rollback();
        out.println("<p>등록 실패입니다!</p>");
        e.printStackTrace();
      }

      out.println("</body>");
      out.println("</html>");
    }
  }
}
