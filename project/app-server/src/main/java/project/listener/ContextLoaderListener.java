package project.listener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import project.dao.*;
import project.util.NcpConfig;
import project.util.NcpObjectStorageService;
import project.util.SqlSessionFactoryProxy;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext ctx = sce.getServletContext();

        try {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryProxy(
                    new SqlSessionFactoryBuilder().build(
                            Resources.getResourceAsStream(ctx.getInitParameter("mybatis-config"))));

            BoardDao boardDao = new MySQLBoardDao(sqlSessionFactory);
            MemberDao memberDao = new MySQLMemberDao(sqlSessionFactory);
            ParkingTimeDao parkingTimeDao = new MySQLParkingTimeDao(sqlSessionFactory);
            NcpObjectStorageService ncpObjectStorageService = new NcpObjectStorageService(new NcpConfig());

            ctx.setAttribute("sqlSessionFactory", sqlSessionFactory);
            ctx.setAttribute("boardDao", boardDao);
            ctx.setAttribute("memberDao", memberDao);
            ctx.setAttribute("parkingTimeDao", parkingTimeDao);
            ctx.setAttribute("ncpObjectStorageService", ncpObjectStorageService);

            System.out.println("ContextLoaderListener.contextInitialized() - 공통 객체 준비 완료!");

        } catch (Exception e) {
            System.out.println("ContextLoaderListener.contextInitialized() - 실행 중 오류 발생!");
            e.printStackTrace();
        }
    }
}
