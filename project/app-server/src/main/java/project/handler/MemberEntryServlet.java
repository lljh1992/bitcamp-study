package project.handler;

import java.io.PrintWriter;
import java.sql.Timestamp;
import org.apache.ibatis.session.SqlSessionFactory;
import project.dao.MemberDao;
import project.util.Component;
import project.util.HttpServletRequest;
import project.util.HttpServletResponse;
import project.util.Servlet;
import project.vo.Member;
import project.vo.ParkingTime;

@Component("/member/entry")
public class MemberEntryServlet implements Servlet {

  MemberDao memberDao;
  SqlSessionFactory sqlSessionFactory;

  public MemberEntryServlet(MemberDao memberDao, SqlSessionFactory sqlSessionFactory) {
    this.memberDao = memberDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

    String carnumber = request.getParameter("carnumber"); // 클라이언트에서 전송한 차량 번호 데이터
    Timestamp entryTime = new Timestamp(System.currentTimeMillis()); // 현재 시간을 입차 시간으로 사용

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

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
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>입차</h1>");

      ParkingTime pt = new ParkingTime();
      pt.setEntryTime(entryTime);// 멤버의 입차 시간을 저장합니다.
      // System.out.println(pt.getEntryTime());

      try {
        pt.saveEntry(entryTime);
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
