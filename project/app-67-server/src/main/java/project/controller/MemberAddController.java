package project.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.ibatis.session.SqlSessionFactory;
import project.dao.MemberDao;
import project.util.NcpObjectStorageService;
import project.vo.Member;

public class MemberAddController implements PageController {

  MemberDao memberDao;
  SqlSessionFactory sqlSessionFactory;
  NcpObjectStorageService ncpObjectStorageService;

public MemberAddController(MemberDao memberDao, SqlSessionFactory sqlSessionFactory, NcpObjectStorageService ncpObjectStorageService) {
  this.memberDao = memberDao;
  this.sqlSessionFactory = sqlSessionFactory;
  this.ncpObjectStorageService = ncpObjectStorageService;
}


  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (request.getMethod().equals("GET")) {
    return "/WEB-INF/jsp/member/form.jsp";
  }
   
    try {
    Member m = new Member();
    m.setBuilding(request.getParameter("building"));
    m.setName(request.getParameter("name"));
    m.setPhonenumber(request.getParameter("phonenumber"));
    m.setPassword(request.getParameter("password"));
    m.setCarnumber(request.getParameter("carnumber"));

    Part photoPart = request.getPart("photo");
    if (photoPart.getSize() > 0) {
      String uploadFileUrl = ncpObjectStorageService.uploadFile("bitcamp-nc7-bucket-16",
          "member2/", photoPart);
      m.setPhoto(uploadFileUrl);
    }

      memberDao.insert(m);
      memberDao.insertCar(m);
      sqlSessionFactory.openSession(false).commit();
      return "redirect:list";

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      request.setAttribute("message", "회원 등록 오류!");
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }
  }
}