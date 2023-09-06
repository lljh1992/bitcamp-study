//package project.handler;
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import project.dao.BoardDao;
//import project.vo.AttachedFile;
//import project.vo.Board;
//import project.vo.Member;
//
//@WebServlet("/board/file/delete")
//public class BoardFileDeleteServlet extends HttpServlet {
//
//  private static final long serialVersionUID = 1L;
//
//  @Override
//  protected void doGet(HttpServletRequest request, HttpServletResponse response)
//      throws ServletException, IOException {
//
//    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
//    if (loginUser == null) {
//      response.sendRedirect("/auth/form.html");
//      return;
//    }
//
//    int category = Integer.parseInt(request.getParameter("category"));
//    int fileNo = Integer.parseInt(request.getParameter("no"));
//
//    BoardDao boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
//    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
//
//    AttachedFile attachedFile = boardDao.findFileBy(fileNo);
//    Board board = boardDao.findBy(category, attachedFile.getBoardNo());
//
//    if (board.getWriter().getNo() != loginUser.getNo()) {
//      throw new ServletException("게시글 변경 권한이 없습니다!");
//    }
//
//    try {
//      if (boardDao.deleteFile(fileNo) == 0) {
//        throw new Exception("해당 번호의 첨부파일이 없거나 삭제 권한이 없습니다.");
//      } else {
//        response.sendRedirect("/board/detail?category=" + category + "&no=" + board.getNo());
//      }
//      sqlSessionFactory.openSession(false).commit();
//
//    } catch (Exception e) {
//      sqlSessionFactory.openSession(false).rollback();
//      throw new RuntimeException(e);
//    }
//  }
//
//}
