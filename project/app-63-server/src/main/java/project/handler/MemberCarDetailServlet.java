package project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;
import project.dao.MySQLParkingTimeDao;
import project.dao.ParkingTimeDao;
import project.vo.ParkingTime;


@WebServlet("/member/detailcar")
public class MemberCarDetailServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr><th>차량번호</th> <th>입차</th> <th>출차</th></tr>");
    out.println("</thead>");
    out.println("<tbody>");

    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
    ParkingTimeDao parkingTimeDao = new MySQLParkingTimeDao(sqlSessionFactory);

    try {
      List<ParkingTime> list = parkingTimeDao.findinout(); // 모든 회원 데이터 가져오기
      for (ParkingTime m : list) {
        out.println("<tr>");
        out.println("<td>" + m.getCarnumber() + "</td>");

        if (m.getEntryTime() != null) {
          out.println("<td>" + m.getEntryTime() + "</td>");
        } else {
          out.println("<td></td>");
        }
        if (m.getExitTime() != null) {
          out.println("<td>" + m.getExitTime() + "</td>");
        } else {
          out.println("<td></td>");
        }
        out.println("<td>");
        out.println("</td>");
        out.println("</tr>");
      }
    } catch (Exception e) {
      out.println("<tr><td colspan='3'>예외 발생: " + e.getMessage() + "</td></tr>");
    }

    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");

    request.getRequestDispatcher("/footer").include(request, response);

    out.println("</body>");
    out.println("</html>");
  }
}


