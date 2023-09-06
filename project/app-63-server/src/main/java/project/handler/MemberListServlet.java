package project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;
import project.dao.MemberDao;
import project.vo.Member;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>입주자</title>");
    out.println("</head>");
    out.println("<body>");

    request.getRequestDispatcher("/header").include(request, response);

    out.println("<h1>입주자 목록</h1>");
    out.println("<div style='margin:5px;'>");
    out.println("<a href='/member/form'>입주자 등록</a>\n");
    out.println("<a href='/member/entry.html'>입차</a>\n");
    out.println("<a href='/member/exit.html'>출차</a>\n");
    out.println("<a href='/member/detailcar'>입출차 기록</a>\n");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr><th>동-호수</th> <th>이름</th> <th>H.P</th> <th>차량 번호</th></tr>");
    out.println("</thead>");

    MemberDao memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");

    List<Member> list = memberDao.findAll();
    for (Member m : list) {
      out.printf("<tr>" + " <td>"
          + "<img src='http://guosqxeocfoi19010728.cdn.ntruss.com/member2/%s?type=f&w=30&h=40&faceopt=true&ttype=jpg'>"
          + "<a href='/member/detail?building=%s'>%s</a></td>" + " <td>%s</td>" + " <td>%s</td>"
          + " <td>%s</td></tr>", m.getPhoto(), m.getBuilding(), m.getBuilding(), m.getName(),
          m.getPhonenumber(), m.getCarnumber());
    }

    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");

    request.getRequestDispatcher("/footer").include(request, response);

    out.println("</body>");
    out.println("</html>");
  }

}
