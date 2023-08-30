package project.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.vo.Member;

@WebServlet("/header")
public class HeaderServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<div style='height:50px;background-color:orange;'>");
    out.println("<img src='/images/logo.png' style='height:40px'>");
    out.println("<a href='/member/list'>아파트 주차관리 시스템</a>");
    out.println("<a href='/board/list?category=1'>민원사항</a>");
    out.println("<a href='/board/list?category=2'>공지사항</a>");

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      out.println("<a href='/auth/form'>로그인</a>");
    } else {
      out.printf("%s %s <a href='/auth/logout'>로그아웃</a>\n", (loginUser.getPhoto() == null
          ? "<img style='height:40px' src='/images/avatar.png'>"
          : String.format(
              "<img src='http://guosqxeocfoi19010728.cdn.ntruss.com/member2/%s?type=f&w=30&h=40&faceopt=true&ttype=jpg'>",
              loginUser.getPhoto())),
          loginUser.getName());
    }

    out.println("</div>");
  }
}
