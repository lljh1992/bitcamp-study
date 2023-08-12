package project.handler;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import project.dao.MemberDao;
import project.util.Component;
import project.util.HttpServletRequest;
import project.util.HttpServletResponse;
import project.util.Servlet;
import project.vo.Member;


@Component("/member/detailcar")
public class MemberCarDetailServlet implements Servlet {

  MemberDao memberDao;
  SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public MemberCarDetailServlet(MemberDao memberDao) {
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
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr><th>차량번호</th> <th>입차</th> <th>출차</th></tr>");
    out.println("</thead>");

    try {
      List<Member> list = memberDao.findinout();
      for (Member m : list) {
        List<Timestamp> entryTimes = m.getEntryTimes();
        out.println("<tr>");
        out.println("<td>" + m.getCarnumber() + "</td>");
        out.println("<td>");

        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Timestamp entryTime : entryTimes) {
          String formattedEntryTime = timeFormatter.format(entryTime);
          out.println(formattedEntryTime + "<br>");
        }

        out.println("</td>");
        out.println("</tr>");
      }
    } catch (Exception e) {
      out.println("<tr><td colspan='3'>예외 발생: " + e.getMessage() + "</td></tr>");
    }

    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");
    out.println("</body>");
    out.println("</html>");
  }
}