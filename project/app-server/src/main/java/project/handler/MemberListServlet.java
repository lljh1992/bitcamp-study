package project.handler;

import java.io.PrintWriter;
import java.util.List;
import project.dao.MemberDao;
import project.util.Component;
import project.util.HttpServletRequest;
import project.util.HttpServletResponse;
import project.util.Servlet;
import project.vo.Member;

@Component("/member/list")
public class MemberListServlet implements Servlet {

  MemberDao memberDao;

  public MemberListServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>입주자</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>입주자 목록</h1>");
    out.println("<div style='margin:5px;'>");
    out.println("<a href='/member/form.html'>입주자 등록</a>\n");
    out.println("<a href='/member/entry.html'>입차</a>\n");
    out.println("<a href='/member/exit.html'>출차</a>\n");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr><th>동-호수</th> <th>이름</th> <th>H.P</th> <th>차량 번호</th></tr>");
    out.println("</thead>");

    List<Member> list = memberDao.findAll();
    for (Member m : list) {
      out.printf(
          "<tr>" + " <td><a href='/member/detail?building=%s'>%s</a></td>" + "<td>%s</td>"
              + " <td>%s</td>" + " <td><a href='/member/detailcar?carnumber=%s'>%s</a></td></tr>",
          m.getBuilding(), m.getBuilding(), m.getName(), m.getPhonenumber(), m.getCarnumber(),
          m.getCarnumber());
    }

    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");
    out.println("</body>");
    out.println("</html>");
  }

}
