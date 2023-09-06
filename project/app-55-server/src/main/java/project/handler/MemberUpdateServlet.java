package project.controller;

import java.io.PrintWriter;
import org.apache.ibatis.session.SqlSessionFactory;
import project.dao.MemberDao;
import project.util.Component;
import project.util.HttpServletRequest;
import project.util.HttpServletResponse;
import project.util.Servlet;
import project.vo.Member;

@Component("/member/update")
public class MemberUpdateServlet implements Servlet {

  MemberDao memberDao;
  SqlSessionFactory sqlSessionFactory;

  public MemberUpdateServlet(MemberDao memberDao, SqlSessionFactory sqlSessionFactory) {
    this.memberDao = memberDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Member member = new Member();
    member.setBuilding(request.getParameter("name"));
    member.setPhonenumber(request.getParameter("H.P"));
    member.setPassword(request.getParameter("password"));
    member.setCarnumber(request.getParameter("carnumber"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='1;url=/member/list'>");
    out.println("<title>입주자</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>입주자 변경</h1>");

    try {
      if (memberDao.update(member) == 0) {
        out.println("<p>회원이 없습니다.</p>");
      } else {
        sqlSessionFactory.openSession(false).commit();
        out.println("<p>변경했습니다!</p>");
      }
    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      out.println("<p>변경 실패입니다!</p>");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");
  }

}


