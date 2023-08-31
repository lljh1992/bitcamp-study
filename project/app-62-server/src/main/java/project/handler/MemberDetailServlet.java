package project.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.vo.Member;

@WebServlet("/member/detail")
public class MemberDetailServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Member member = InitServlet.memberDao.findBy(request.getParameter("building"));

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

    out.println("<h1>입주자</h1>");

    if (member == null) {
      out.println("<p>해당 번호의 회원이 없습니다!</p>");

    } else {
      out.println("<form action='/member/update' method='post'> enctype='multipart/form-data'>");
      out.println("<table border='1'>");
      out.printf("<tr><th style='width:120px;'>사진</th>" + " <td style='width:300px;'>"
          + (member.getPhoto() == null ? "<img style='height:80px' src='/images/avatar.png'>"
              : "<a href='https://kr.object.ncloudstorage.com/bitcamp-nc7-bucket-16/member2/%s'>"
                  + "<img src='http://guosqxeocfoi19010728.cdn.ntruss.com/member2/%1$s?type=f&w=30&h=40&faceopt=true&ttype=jpg'>"
                  + "</a>")
          + " <input type='file' name='photo'>" + " </td></tr>\n", member.getPhoto());

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

    request.getRequestDispatcher("/footer").include(request, response);

    out.println("</body>");
    out.println("</html>");

  }
}