/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import project.dao.BoardDao;
import project.dao.MemberDao;
import project.dao.MySQLBoardDao;
import project.dao.MySQLMemberDao;
import project.controller.BoardAddListener;
import project.controller.BoardDeleteListener;
import project.controller.BoardDetailListener;
import project.controller.BoardListListener;
import project.controller.BoardUpdateListener;
import project.controller.LoginListener;
import project.controller.MemberAddListener;
import project.controller.MemberDeleteListener;
import project.controller.MemberDetailListener;
import project.controller.MemberEntryListener;
import project.controller.MemberExitListener;
import project.controller.MemberListListener;
import project.controller.MemberUpdateListener;
import project.net.NetProtocol;
import project.util.BreadcrumbPrompt;
import project.util.Menu;
import project.util.MenuGroup;
import project.util.SqlSessionFactoryProxy;

public class ServerApp {

  ExecutorService threadPool = Executors.newFixedThreadPool(2);

  SqlSessionFactory sqlSessionFactory;

  MemberDao memberDao;
  BoardDao boardDao;

  MenuGroup mainMenu = new MenuGroup("로비");

  int port;


  public ServerApp(int port) throws Exception {

    this.port = port;
    InputStream mybatisConfigIn =
        Resources.getResourceAsStream("project/config/mybatis-config.xml");

    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

    sqlSessionFactory = new SqlSessionFactoryProxy(builder.build(mybatisConfigIn));

    this.memberDao = new MySQLMemberDao(sqlSessionFactory);
    this.boardDao = new MySQLBoardDao(sqlSessionFactory);

    prepareMenu();
  }

  public void close() throws Exception {}

  public static void main(String[] args) throws Exception {
    ServerApp app = new ServerApp(8888);
    app.execute();
    app.close();
  }


  public void execute() {
    try (ServerSocket serverSocket = new ServerSocket(this.port)) {
      System.out.println("서버 실행 중...");

      while (true) {
        Socket socket = serverSocket.accept();
        threadPool.execute(() -> processRequest(socket));
      }
    } catch (Exception e) {
      System.out.println("서버 실행 오류!");
      e.printStackTrace();
    }
  }

  private void processRequest(Socket socket) {
    try (Socket s = socket;
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

      BreadcrumbPrompt prompt = new BreadcrumbPrompt(in, out);

      InetSocketAddress clientAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
      System.out.printf("%s 클라이언트 접속함!\n", clientAddress.getHostString());

      out.writeUTF("[주차 관리 시스템]\n" + "-----------------------------------------");

      new LoginListener(memberDao).service(prompt);

      mainMenu.execute(prompt);
      out.writeUTF(NetProtocol.NET_END);

    } catch (Exception e) {
      System.out.println("클라이언트 통신 오류!");
      e.printStackTrace();
    } finally {
      ((SqlSessionFactoryProxy) sqlSessionFactory).clean();
    }
  }

  private void prepareMenu() {

    MenuGroup memberMenu = new MenuGroup("주차 관리 시스템");
    memberMenu.add(new Menu("차량 등록", new MemberAddListener(memberDao, sqlSessionFactory)));
    memberMenu.add(new Menu("차량 목록", new MemberListListener(memberDao)));
    memberMenu.add(new Menu("차량 조회", new MemberDetailListener(memberDao)));
    memberMenu.add(new Menu("정보 변경", new MemberUpdateListener(memberDao, sqlSessionFactory)));
    memberMenu.add(new Menu("정보 삭제", new MemberDeleteListener(memberDao, sqlSessionFactory)));
    memberMenu.add(new Menu("차량 입차 기록", new MemberEntryListener(memberDao)));
    memberMenu.add(new Menu("차량 출차 기록", new MemberExitListener(memberDao)));
    mainMenu.add(memberMenu);

    MenuGroup boardMenu = new MenuGroup("민원사항");
    boardMenu.add(new Menu("민원사항 등록", new BoardAddListener(1, boardDao, sqlSessionFactory)));
    boardMenu.add(new Menu("민원사항 목록", new BoardListListener(1, boardDao)));
    boardMenu.add(new Menu("민원사항 조회", new BoardDetailListener(1, boardDao, sqlSessionFactory)));
    boardMenu.add(new Menu("민원사항 변경", new BoardUpdateListener(1, boardDao, sqlSessionFactory)));
    boardMenu.add(new Menu("민원사항 삭제", new BoardDeleteListener(1, boardDao, sqlSessionFactory)));
    mainMenu.add(boardMenu);

    MenuGroup noticeMenu = new MenuGroup("공지사항");
    noticeMenu.add(new Menu("공지사항 등록", new BoardAddListener(2, boardDao, sqlSessionFactory)));
    noticeMenu.add(new Menu("공지사항 목록", new BoardListListener(2, boardDao)));
    noticeMenu.add(new Menu("공지사항 조회", new BoardDetailListener(2, boardDao, sqlSessionFactory)));
    noticeMenu.add(new Menu("공지사항 변경", new BoardUpdateListener(2, boardDao, sqlSessionFactory)));
    noticeMenu.add(new Menu("공지사항 삭제", new BoardDeleteListener(2, boardDao, sqlSessionFactory)));
    mainMenu.add(noticeMenu);

    // Menu helloMenu = new Menu("안녕하세요.");
    // helloMenu.addActionListener(new HeaderListener());
    // helloMenu.addActionListener(new HelloListener());
    // helloMenu.addActionListener(new FooterListener());
    // mainMenu.add(helloMenu);

  }
}
