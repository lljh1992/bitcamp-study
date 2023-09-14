package project.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.ibatis.session.SqlSessionFactory;
import project.dao.BoardDao;
import project.util.NcpObjectStorageService;
import project.vo.AttachedFile;
import project.vo.Board;
import project.vo.Member;

public class BoardUpdateController implements PageController {

    BoardDao boardDao;
    SqlSessionFactory sqlSessionFactory;
    NcpObjectStorageService ncpObjectStorageService;

    public BoardUpdateController(BoardDao boardDao, SqlSessionFactory sqlSessionFactory, NcpObjectStorageService ncpObjectStorageService) {
        this.boardDao = boardDao;
        this.sqlSessionFactory = sqlSessionFactory;
        this.ncpObjectStorageService = ncpObjectStorageService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Member loginUser = (Member) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            request.getParts();
            return "redirect:../auth/login";
        }

        try {
            Board board = new Board();
            board.setWriter(loginUser);
            board.setNo(Integer.parseInt(request.getParameter("no")));
            board.setTitle(request.getParameter("title"));
            board.setContent(request.getParameter("content"));
            board.setCategory(Integer.parseInt(request.getParameter("category")));

            ArrayList<AttachedFile> attachedFiles = new ArrayList<>();
            for (Part part : request.getParts()) {
                if (part.getName().equals("files") && part.getSize() > 0) {
                    String uploadFileUrl = ncpObjectStorageService
                            .uploadFile("bitcamp-nc7-bucket-16", "board2/", part);
                    AttachedFile attachedFile = new AttachedFile();
                    attachedFile.setFilePath(uploadFileUrl);
                    attachedFiles.add(attachedFile);
                }
            }
            board.setAttachedFiles(attachedFiles);

            if (boardDao.update(board) == 0) {
                throw new Exception("게시글이 없거나 변경 권한이 없습니다.");
            } else {
                if (attachedFiles.size() > 0) {
                    boardDao.insertFiles(board);
                }

                sqlSessionFactory.openSession(false).commit();
                return "redirect:list?category=" + request.getParameter("category");
            }

        } catch (Exception e) {
            sqlSessionFactory.openSession(false).rollback();
            request.setAttribute("refresh", "2;url=detail?category=" + request.getParameter("category") +
                    "&no=" + request.getParameter("no"));
            throw e;
        }
    }
}