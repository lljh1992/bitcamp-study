package project.servlet;

import org.apache.ibatis.session.SqlSessionFactory;
import project.controller.*;
import project.dao.BoardDao;
import project.dao.MemberDao;
import project.dao.ParkingTimeDao;
import project.util.NcpObjectStorageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/app/*")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class DispatcherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    Map<String, PageController> controllerMap = new HashMap<>();

    @Override
    public void init() throws ServletException {
        MemberDao memberDao = (MemberDao) this.getServletContext().getAttribute("memberDao");
        BoardDao boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
        ParkingTimeDao parkingTimeDao = (ParkingTimeDao) this.getServletContext().getAttribute("parkingTimeDao");
        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
        NcpObjectStorageService ncpObjectStorageService = (NcpObjectStorageService) this.getServletContext().getAttribute("ncpObjectStorageService");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        controllerMap.put("/", new HomeController());
        controllerMap.put("/auth/login", new LoginController(memberDao));
        controllerMap.put("/auth/logout", new LogoutController());
        controllerMap.put("/member/list", new MemberListController(memberDao));
        controllerMap.put("/member/add", new MemberAddController(memberDao, sqlSessionFactory, ncpObjectStorageService));
        controllerMap.put("/member/detail", new MemberDetailController(memberDao));
        controllerMap.put("/member/update", new MemberUpdateController(memberDao, sqlSessionFactory, ncpObjectStorageService));
        controllerMap.put("/member/delete", new MemberDeleteController(memberDao, sqlSessionFactory));
        controllerMap.put("/member/detailcar", new MemberCarDetailController(parkingTimeDao, dateFormatter));
        controllerMap.put("/member/entry", new MemberEntryController(sqlSessionFactory, parkingTimeDao, memberDao));
        controllerMap.put("/member/exit", new MemberExitController(sqlSessionFactory, parkingTimeDao, memberDao));
        controllerMap.put("/board/list", new BoardListController(boardDao));
        controllerMap.put("/board/add", new BoardAddController(boardDao, sqlSessionFactory, ncpObjectStorageService));
        controllerMap.put("/board/detail", new BoardDetailController(boardDao, sqlSessionFactory));
        controllerMap.put("/board/update", new BoardUpdateController(boardDao, sqlSessionFactory, ncpObjectStorageService));
        controllerMap.put("/board/delete", new BoardDeleteController(boardDao, sqlSessionFactory));
        controllerMap.put("/board/fileDelete", new BoardFileDeleteController(boardDao, sqlSessionFactory));
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pageControllerPath = request.getPathInfo();

        response.setContentType("text/html;charset=UTF-8");

        PageController pageController = controllerMap.get(pageControllerPath);
        if (pageController == null) {
            throw new ServletException("해당 요청을 처리할 수 없습니다.");
        }

        try {

        String viewUrl = pageController.execute(request, response);
        if (viewUrl.startsWith("redirect:")) {
            response.sendRedirect(viewUrl.substring(9)); // 예) redirect:/app/board/list
        } else {
            request.getRequestDispatcher(viewUrl).include(request, response);
        }
    } catch (Exception e) {
        throw new ServletException("요청 처리 중 오류 발생", e);
    }
    }
}
