package project.listener;


import project.util.SqlSessionFactoryProxy;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class MyServletRequestListener implements ServletRequestListener {

  public MyServletRequestListener() {
    System.out.println("MyServletRequestListener 객체 생성되었네!");
  }

  @Override
  public void requestDestroyed(ServletRequestEvent sre) {
    // 클라이언트 요청에 대한 응답을 완료하면
    // 요청을 처리하는 동안 스레드가 사용했던 SqlSession 객체를 스레드에서 제거한다.
    SqlSessionFactoryProxy sqlSessionFactoryProxy = (SqlSessionFactoryProxy) sre.getServletContext().getAttribute("sqlSessionFactory");
    sqlSessionFactoryProxy.clean();
  }

}
