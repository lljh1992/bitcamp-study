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

@WebServlet("/member/update")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class MemberUpdateController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MemberDao memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
        NcpObjectStorageService ncpObjectStorageService = (NcpObjectStorageService) this.getServletContext().getAttribute("ncpObjectStorageService");


        try {
            Member member = new Member();
            member.setBuilding(request.getParameter("building"));
            member.setName(request.getParameter("name"));
            member.setPhonenumber(request.getParameter("phonenumber"));
            member.setPassword(request.getParameter("password"));
            member.setCarnumber(request.getParameter("carnumber"));

            Part photoPart = request.getPart("photo");
            if (photoPart.getSize() > 0) {
                String uploadFileUrl = ncpObjectStorageService.uploadFile("bitcamp-nc7-bucket-16",
                        "member2/", photoPart);
                member.setPhoto(uploadFileUrl);
            }

            if (memberDao.update(member) == 0) {
                throw new Exception("회원이 없습니다.");
            } else {
                sqlSessionFactory.openSession(false).commit();
                response.sendRedirect("list");
            }
        } catch (Exception e) {
            sqlSessionFactory.openSession(false).rollback();
            request.setAttribute("refresh", "2;url=list");
            throw new ServletException(e);
        }
    }

}


