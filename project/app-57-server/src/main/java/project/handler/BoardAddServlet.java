package project.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import project.vo.AttachedFile;
import project.vo.Board;
import project.vo.Member;

@WebServlet("/board/add")
public class BoardAddServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }

    try {
      DiskFileItemFactory factory = new DiskFileItemFactory();
      ServletFileUpload upload = new ServletFileUpload(factory);
      List<FileItem> parts = upload.parseRequest(request);

      Board board = new Board();
      board.setWriter(loginUser);

      ServletContext 웹애플리케이션환경정보 = request.getServletContext();
      String uploadDir = 웹애플리케이션환경정보.getRealPath("/upload/board/");

      ArrayList<AttachedFile> attachedFiles = new ArrayList<>();

      for (FileItem part : parts) {
        if (part.isFormField()) {
          if (part.getFieldName().equals("title")) {
            board.setTitle(part.getString("UTF-8"));
          } else if (part.getFieldName().equals("content")) {
            board.setContent(part.getString("UTF-8"));
          } else if (part.getFieldName().equals("category")) {
            board.setCategory(Integer.parseInt(part.getString("UTF-8")));
          }
        } else {
          String filename = UUID.randomUUID().toString();
          part.write(new File(uploadDir, filename));
          AttachedFile attachedFile = new AttachedFile();
          attachedFile.setFilePath(filename);

          attachedFiles.add(attachedFile);
        }
      }
      board.setAttachedFiles(attachedFiles);

      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.printf("<meta http-equiv='refresh' content='1;url=/board/list?category=%d'>\n",
          board.getCategory());
      out.println("<title>민원사항</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>민원사항 등록</h1>");
      try {
        InitServlet.boardDao.insert(board);
        int count = InitServlet.boardDao.insertFiles(board);
        System.out.println(count);
        InitServlet.sqlSessionFactory.openSession(false).commit();
        out.println("<p>등록 성공입니다!</p>");

      } catch (Exception e) {
        InitServlet.sqlSessionFactory.openSession(false).rollback();
        out.println("<p>등록 실패입니다!</p>");
        e.printStackTrace();
      }
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
