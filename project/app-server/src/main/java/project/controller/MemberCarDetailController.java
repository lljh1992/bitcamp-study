package project.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.dao.ParkingTimeDao;



public class MemberCarDetailController implements PageController {

    ParkingTimeDao parkingTimeDao;
    SimpleDateFormat dateFormatter;

    public MemberCarDetailController(ParkingTimeDao parkingTimeDao, SimpleDateFormat dateFormatter) {
        this.parkingTimeDao = parkingTimeDao;
        this.dateFormatter = dateFormatter;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
        request.setAttribute("list", parkingTimeDao.findinout());
        return "/WEB-INF/jsp/member/detailcar.jsp";

    }
}


