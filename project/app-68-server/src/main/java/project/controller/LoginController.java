package project.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import project.dao.MemberDao;
import project.vo.Member;

@Component("/auth/login")
public class LoginController implements PageController {

    MemberDao memberDao;

    public LoginController(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {
            return "/WEB-INF/jsp/auth/form.jsp";
        }

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

        Member loginUser = memberDao.findByPhonenumberAndPassword(m);
        if (loginUser == null) {

            request.setAttribute("refresh", "2;url=/app/auth/login");
            throw new Exception("회원 정보가 일치하지 않습니다.");
        }
        request.getSession().setAttribute("loginUser", loginUser);
        return "redirect:/";
    }
}