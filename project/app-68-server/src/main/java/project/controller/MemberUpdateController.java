package project.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import project.dao.MemberDao;
import project.service.NcpObjectStorageService;
import project.vo.Member;

@Component("/member/update")
public class MemberUpdateController implements PageController {

    MemberDao memberDao;
    SqlSessionFactory sqlSessionFactory;
    NcpObjectStorageService ncpObjectStorageService;

    public MemberUpdateController(MemberDao memberDao, SqlSessionFactory sqlSessionFactory, NcpObjectStorageService ncpObjectStorageService) {
        this.memberDao = memberDao;
        this.sqlSessionFactory = sqlSessionFactory;
        this.ncpObjectStorageService = ncpObjectStorageService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

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
                return "redirect:list";
            }
        } catch (Exception e) {
            sqlSessionFactory.openSession(false).rollback();
            request.setAttribute("refresh", "2;url=list");
            throw e;
        }
    }
}
