// 쿠키(cookie) 읽기 - 사용 범위가 지정된 쿠키 읽기
package eomcs.servlet.ex10;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ex10/s22")
@SuppressWarnings("serial")
public class Servlet22 extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 테스트 방법:
    // => http://localhost:8080/java-web/ex10/s22
    //

    Cookie[] cookies = request.getCookies();

    response.setContentType("text/plain;charset=UTF-8");
    PrintWriter out = response.getWriter();

    if (cookies != null) {
      for (Cookie c : cookies) {
        out.printf("%s=%s\n", c.getName(), c.getValue());
      }
    }

  }
}


