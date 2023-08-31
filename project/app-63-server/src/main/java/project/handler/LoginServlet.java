package project.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.dao.MemberDao;
import project.vo.Member;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Member m = new Member();
        m.setPhonenumber(request.getParameter("phonenumber"));
        m.setPassword(request.getParameter("password"));

        if (request.getParameter("savePhonenumber") != null) {
            Cookie cookie = new Cookie("phonenumber", m.getPhonenumber());
            response.addCookie(cookie);
        } else {
            Cookie cookie = new Cookie("phonenumber", "no");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        MemberDao memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
        Member loginUser = memberDao.findByPhonenumberAndPassword(m);
        if (loginUser != null) {
            // 로그인 정보를 다른 요청에서도 사용할 있도록 세션 보관소에 담아 둔다.
            request.getSession().setAttribute("loginUser", loginUser);
            response.sendRedirect("/");
            return;
        }

        request.setAttribute("message", "회원 정보가 일치하지 않습니다.");
        request.setAttribute("refresh", "1;url=/auth/form.html");

        request.getRequestDispatcher("/error").forward(request, response);
    }
}