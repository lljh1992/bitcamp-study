/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package project;

import java.sql.Connection;
import java.sql.DriverManager;
import project.dao.BoardDao;
import project.dao.MemberDao;
import project.dao.MySQLBoardDao;
import project.dao.MySQLMemberDao;
import project.controller.BoardAddListener;
import project.controller.BoardDeleteListener;
import project.controller.BoardDetailListener;
import project.controller.BoardListListener;
import project.controller.BoardUpdateListener;
import project.controller.FooterListener;
import project.controller.HeaderListener;
import project.controller.HelloListener;
import project.controller.MemberAddListener;
import project.controller.MemberDeleteListener;
import project.controller.MemberDetailListener;
import project.controller.MemberEntryListener;
import project.controller.MemberExitListener;
import project.controller.MemberListListener;
import project.controller.MemberLoginListener;
import project.controller.MemberUpdateListener;
import project.controller.NewMemberListListener;
import project.util.BreadcrumbPrompt;
import project.util.Menu;
import project.util.MenuGroup;

public class ClientApp {

  static MemberDao memberDao;
  static BoardDao boardDao;
  static BoardDao noticeDao;

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");

  public ClientApp(String ip, int port) throws Exception {

    Connection con = DriverManager.getConnection("jdbc:mysql://study:1111@localhost:3306/projectdb" // JDBC
    // URL
    );

    memberDao = new MySQLMemberDao(con);
    boardDao = new MySQLBoardDao(con, 1);
    noticeDao = new MySQLBoardDao(con, 2);

    prepareMenu();
  }

  public void close() throws Exception {
    prompt.close();
  }

  public static void main(String[] args) throws Exception {
    if (args.length < 2) {
      System.out.println("실행 예) java ... project.ClientApp 서버주소 포트번호");
      return;
    }

    ClientApp app = new ClientApp(args[0], Integer.parseInt(args[1]));
    app.execute();
    app.close();
  }

  static void printTitle() {
    System.out.println("--------------------------");
    System.out.println("     주차 관리 시스템     ");
    System.out.println("--------------------------");
  }

  public void execute() {

    printTitle();

    mainMenu.execute(prompt);

  }

  public void prepareMenu() {
    MenuGroup memberMenu = new MenuGroup("주차 관리 시스템");
    memberMenu.add(new Menu("회원가입", new MemberAddListener(memberDao)));
    memberMenu.add(new Menu("로그인", new MemberLoginListener(memberDao)));
    memberMenu.add(new Menu("회원정보 변경", new MemberUpdateListener(memberDao)));
    memberMenu.add(new Menu("회원정보 조회", new NewMemberListListener(memberDao)));
    memberMenu.add(new Menu("회원탈퇴", new MemberDeleteListener(memberDao)));
    mainMenu.add(memberMenu);

    Menu helloMenu = new Menu("안녕하세요.");
    helloMenu.addActionListener(new HeaderListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FooterListener());
    mainMenu.add(helloMenu);

  }


  public static void service(BreadcrumbPrompt prompt) {

    MenuGroup mainMenu = new MenuGroup("로비");

    MenuGroup memberMenu = new MenuGroup("주차관리");
    memberMenu.add(new Menu("차량 목록", new MemberListListener(memberDao)));
    memberMenu.add(new Menu("차량 조회", new MemberDetailListener(memberDao)));
    memberMenu.add(new Menu("차량 입차 기록", new MemberEntryListener(memberDao)));
    memberMenu.add(new Menu("차량 출차 기록", new MemberExitListener(memberDao)));
    mainMenu.add(memberMenu);

    MenuGroup boardMenu = new MenuGroup("민원사항");
    boardMenu.add(new Menu("민원사항 등록", new BoardAddListener(boardDao)));
    boardMenu.add(new Menu("민원사항 목록", new BoardListListener(boardDao)));
    boardMenu.add(new Menu("민원사항 조회", new BoardDetailListener(boardDao)));
    boardMenu.add(new Menu("민원사항 변경", new BoardUpdateListener(boardDao)));
    boardMenu.add(new Menu("민원사항 삭제", new BoardDeleteListener(boardDao)));
    mainMenu.add(boardMenu);

    MenuGroup noticeMenu = new MenuGroup("공지사항");
    noticeMenu.add(new Menu("공지사항 등록", new BoardAddListener(noticeDao)));
    noticeMenu.add(new Menu("공지사항 목록", new BoardListListener(noticeDao)));
    noticeMenu.add(new Menu("공지사항 조회", new BoardDetailListener(noticeDao)));
    noticeMenu.add(new Menu("공지사항 변경", new BoardUpdateListener(noticeDao)));
    noticeMenu.add(new Menu("공지사항 삭제", new BoardDeleteListener(noticeDao)));
    mainMenu.add(noticeMenu);

    mainMenu.execute(prompt);
  }

}
