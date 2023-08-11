package project.handler;

import java.io.PrintWriter;
import java.sql.Timestamp;
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

  public MemberCarDetailServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

    Member member = memberDao.findByCar(request.getParameter("carnumber"));

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
      out.printf(
          "<tr><th>차량 번호</th>"
              + " <td><input type='carnumber' name='carnumber' value='%s'></td></tr>\n",
          member.getCarnumber());

    }
    List<Member> list = memberDao.findinout();
    for (int i = 0; i < list.size(); i++) {
      List<Timestamp> entryTimes = member.getEntryTimes();
      out.println("<tr><th>입차 시간</th><td>");
      for (Timestamp entryTime : entryTimes) {
        out.printf("<input type='datetime' name='entryTime' value='%tY-%<tm-%<td %<tT'><br>\n",
            entryTime);
      }
      out.println("</td></tr>");
    }



    out.println("</table>");

    out.println("<div>");
    out.println("<button>변경</button>");
    out.println("<button type='reset'>초기화</button>");
    out.printf("<a href='/member/delete?building=%s'>삭제</a>\n", member.getBuilding());
    out.println("<a href='/member/list'>목록</a>\n");
    out.println("</div>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }

}
