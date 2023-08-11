package project.handler;

import java.io.PrintWriter;
import org.apache.ibatis.session.SqlSessionFactory;
import project.dao.MemberDao;
import project.util.Component;
import project.util.HttpServletRequest;
import project.util.HttpServletResponse;
import project.util.Servlet;
import project.vo.Member;

@Component("/member/exit")
public class MemberExitServlet implements Servlet {

  MemberDao memberDao;
  SqlSessionFactory sqlSessionFactory;

  public MemberExitServlet(MemberDao memberDao, SqlSessionFactory sqlSessionFactory) {
    this.memberDao = memberDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

    Member member = memberDao.findByCar(request.getParameter("carnumber"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    if (member == null) {
      out.println("<p>이 번호로 등록된 차량이 없습니다!</p>");
    } else {
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='refresh' content='1;url=/member/list'>");
      out.println("<title>아파트 주차 관리 시스템</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>출차</h1>");

      try {
        member.getExitTimes();
        member.printExitTimes();
        memberDao.saveExit(member); // 차량번호에 해당하는 멤버의 입차 시간을 저장합니다.
        sqlSessionFactory.openSession(false).commit();
        out.println("<p>등록 성공입니다!</p>");

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
