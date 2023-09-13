package project.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import project.dao.ParkingTimeDao;

@Component("/member/detailcar")
public class MemberCarDetailController implements PageController {

    ParkingTimeDao parkingTimeDao;

    public MemberCarDetailController(ParkingTimeDao parkingTimeDao) {
        this.parkingTimeDao = parkingTimeDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        request.setAttribute("list", parkingTimeDao.findinout());
        return "/WEB-INF/jsp/member/detailcar.jsp";

    }
}


