package project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;
import project.dao.BoardDao;
import project.vo.AttachedFile;
import project.vo.Board;

@WebServlet("/board/detail")
public class BoardDetailController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BoardDao boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");

        try {
            int category = Integer.parseInt(request.getParameter("category"));
            int no = Integer.parseInt(request.getParameter("no"));

            Board board = boardDao.findBy(category, no);

            if (board != null) {
                board.setViewCount(board.getViewCount() + 1);
                boardDao.updateCount(board);
                sqlSessionFactory.openSession(false).commit();
                request.setAttribute("board", board);
            }
            response.setContentType("text/html;charset=UTF-8");
            request.getRequestDispatcher("/WEB-INF/jsp/board/detail.jsp").include(request, response);

        } catch (Exception e) {
            sqlSessionFactory.openSession(false).rollback();
            request.setAttribute("refresh", "5;url=/board/list?category=" + request.getParameter("category"));
            throw new ServletException(e);
        }
    }
}
