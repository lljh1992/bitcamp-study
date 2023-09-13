package project.controller;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import project.dao.MemberDao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/member/delete")
public class MemberDeleteController implements PageController {

  MemberDao memberDao;
  SqlSessionFactory sqlSessionFactory;
  
  public MemberDeleteController(MemberDao memberDao, SqlSessionFactory sqlSessionFactory) {
    this.memberDao = memberDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }


  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    
    try {
      if (memberDao.delete(request.getParameter("building")) == 0) {
        throw new Exception("입주자가 없습니다.");
      } else {
        sqlSessionFactory.openSession(false).commit();
        return "redirect:list";
      }

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }
  }
}
