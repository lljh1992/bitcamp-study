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
    out.println("<h1>입주자 목록</h1>");
    out.println("<div style='margin:5px;'>");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr><th>차량번호</th> <th>입차</th> <th>출차</th></tr>");
    out.println("</thead>");
    out.println("<tbody>");

    try {
      List<ParkingTime> list = InitServlet.parkingTimeDao.findinout(); // 모든 회원 데이터 가져오기

      for (ParkingTime m : list) {
        // List<Timestamp> entryTimes = m.getEntryTimes();
        // List<Timestamp> exitTimes = m.getExitTimes();

        out.println("<tr>");
        out.println("<td>" + m.getCarnumber() + "</td>");
        out.println("<td>" + m.getEntryTime() + "</td>");
        out.println("<td>" + m.getExitTime() + "</td>");
        out.println("<td>");

        // SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //
        // for (Timestamp entryTime : entryTimes) {
        // String formattedEntryTime = timeFormatter.format(entryTime);
        // out.println(formattedEntryTime + "<br>");
        // }
        //
        // out.println("</td>");
        // out.println("<td>");
        //
        // for (Timestamp exitTime : exitTimes) {
        // String formattedExitTime = timeFormatter.format(exitTime);
        // out.println(formattedExitTime + "<br>");
        // }

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
// response.setContentType("text/html;charset=UTF-8");
// PrintWriter out = response.getWriter();
// out.println("<!DOCTYPE html>");
// out.println("<html>");
// out.println("<head>");
// out.println("<meta charset='UTF-8'>");
// out.println("<title>입주자</title>");
// out.println("</head>");
// out.println("<body>");
// out.println("<h1>입주자 목록</h1>");
// out.println("<div style='margin:5px;'>");
// out.println("</div>");
// out.println("<table border='1'>");
// out.println("<thead>");
// out.println(" <tr><th>차량번호</th> <th>입차</th> <th>출차</th></tr>");
// out.println("</thead>");
// out.println("<tbody>");
//
// try {
// List<Member> list = memberDao.findinout();
// for (Member m : list) {
// List<Timestamp> entryTimes = m.getEntryTimes();
// List<Timestamp> exitTimes = m.getExitTimes();
// out.println("<tr>");
// out.println("<td>" + m.getCarnumber() + "</td>");
// out.println("<td>");
//
// SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
// for (Timestamp entryTime : entryTimes) {
// System.out.println("Number of entries: " + entryTimes.size());
// String formattedEntryTime = timeFormatter.format(entryTime);
// out.println(formattedEntryTime + "<br>");
// }
//
// out.println("</td>");
// out.println("<td>");
//
// for (Timestamp exitTime : exitTimes) {
// String formattedExitTime = timeFormatter.format(exitTime);
// out.println(formattedExitTime + "<br>");
// }
//
// out.println("</td>");
// out.println("</tr>");
// }
// } catch (Exception e) {
// out.println("<tr><td colspan='3'>예외 발생: " + e.getMessage() + "</td></tr>");
// }
//
// out.println("</tbody>");
// out.println("</table>");
// out.println("<a href='/'>메인</a>");
// out.println("</body>");
// out.println("</html>");
// }
// }

