package project.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.dao.ParkingTimeDao;


@WebServlet("/member/detailcar")
public class MemberCarDetailController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ParkingTimeDao parkingTimeDao = (ParkingTimeDao) this.getServletContext().getAttribute("parkingTimeDao");
        request.setAttribute("list", parkingTimeDao.findinout());
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/WEB-INF/jsp/member/detailcar.jsp").include(request, response);

    }
}


