package project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;
import project.dao.MemberDao;
import project.dao.MySQLParkingTimeDao;
import project.dao.ParkingTimeDao;
import project.vo.Member;
import project.vo.ParkingTime;

public class MemberExitController implements PageController {

  SqlSessionFactory sqlSessionFactory;
  ParkingTimeDao parkingTimeDao;
  MemberDao memberDao;

  public MemberExitController(SqlSessionFactory sqlSessionFactory, ParkingTimeDao parkingTimeDao, MemberDao memberDao) {
    this.sqlSessionFactory = sqlSessionFactory;
    this.parkingTimeDao = parkingTimeDao;
    this.memberDao = memberDao;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    if (request.getMethod().equals("GET")) {
      return "/WEB-INF/jsp/member/exit.jsp";
    }

    String carnumber = request.getParameter("carnumber"); // 클라이언트에서 전송한 차량 번호 데이터
    Timestamp exitTime = new Timestamp(System.currentTimeMillis()); // 현재 시간을 입차 시간으로 사용

    response.setContentType("text/html;charset=UTF-8");

    // 차량 번호로 회원 정보 조회
    Member member = memberDao.findByCar(carnumber);

      ParkingTime pt = new ParkingTime();
      pt.setCarnumber(carnumber);
      pt.setExitTime(exitTime);

      try {
        parkingTimeDao.saveExit(pt);
        sqlSessionFactory.openSession(false).commit();
        return "redirect:list";

      } catch (Exception e) {
        sqlSessionFactory.openSession(false).rollback();
        request.setAttribute("refresh", "2;url=list");
        throw e;
      }
    }
  }
