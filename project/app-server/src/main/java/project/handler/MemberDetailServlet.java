package project.handler;

import java.io.PrintWriter;
import project.dao.MemberDao;
import project.util.Component;
import project.util.HttpServletRequest;
import project.util.HttpServletResponse;
import project.util.Servlet;
import project.vo.Member;

@Component("/member/detail")
public class MemberDetailServlet implements Servlet {

  MemberDao memberDao;

  public MemberDetailServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

    Member member = memberDao.findBy(request.getParameter("building"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>입주자</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>입주자</h1>");

    if (member == null) {
      out.println("<p>해당 번호의 회원이 없습니다!</p>");

    } else {
      out.println("<form action='/member/update' method='post'>");
      out.println("<table border='1'>");
      // out.printf("<tr><th style='width:120px;'>번호</th>"
      // + " <td style='width:300px;'><input type='text' name='no' value='%d'
      // readonly></td></tr>\n",
      // member.getNo());
      out.printf("<tr><th>이름</th>" + " <td><input type='text' name='name' value='%s'></td></tr>\n",
          member.getName());
      out.printf(
          "<tr><th>핸드폰번호</th>"
              + " <td><input type='phonenumber' name='phonenumber' value='%s'></td></tr>\n",
          member.getPhonenumber());
      out.printf(
          "<tr><th>차량 번호</th>"
              + " <td><input type='carnumber' name='carnumber' value='%s'></td></tr>\n",
          member.getCarnumber());
      out.printf(
          "<tr><th>동-호수</th>"
              + " <td><input type='building' name='building' value='%s'></td></tr>\n",
          member.getBuilding());
      out.println("<tr><th>암호</th>" + " <td><input type='password' name='password'></td></tr>");

      out.println("</table>");

      out.println("<div>");
      out.println("<button>변경</button>");
      out.println("<button type='reset'>초기화</button>");
      out.printf("<a href='/member/delete?building=%s'>삭제</a>\n", member.getBuilding());
      out.println("<a href='/member/list'>목록</a>\n");
      out.println("</div>");
      out.println("</form>");
    }

    out.println("</body>");
    out.println("</html>");

  }
}
