/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import project.dao.BoardDao;
import project.dao.DaoBuilder;
import project.dao.MemberDao;
import project.dao.NewMemberDao;
import project.handler.BoardAddListener;
import project.handler.BoardDeleteListener;
import project.handler.BoardDetailListener;
import project.handler.BoardListListener;
import project.handler.BoardUpdateListener;
import project.handler.FooterListener;
import project.handler.HeaderListener;
import project.handler.HelloListener;
import project.handler.MemberAddListener;
import project.handler.MemberDeleteListener;
import project.handler.MemberDetailListener;
import project.handler.MemberEntryListener;
import project.handler.MemberExitListener;
import project.handler.MemberListListener;
import project.handler.MemberUpdateListener;
import project.handler.NewMemberAddListener;
import project.handler.NewMemberDeleteListener;
import project.handler.NewMemberListListener;
import project.handler.NewMemberLoginListener;
import project.handler.NewMemberUpdateListener;
import project.net.RequestEntity;
import project.util.BreadcrumbPrompt;
import project.util.Menu;
import project.util.MenuGroup;
import project.vo.Member;

public class ClientApp {

  Socket socket;
  DataOutputStream out;
  DataInputStream in;

  static ArrayList<Member> memberList = new ArrayList<>();

  NewMemberDao newmemberDao;
  static MemberDao memberDao;
  static BoardDao boardDao;
  static BoardDao noticeDao;

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");


  public ClientApp(String ip, int port) throws Exception {

    this.socket = new Socket(ip, port);
    this.out = new DataOutputStream(socket.getOutputStream());
    this.in = new DataInputStream(socket.getInputStream());

    DaoBuilder daoBuilder = new DaoBuilder(in, out);

    this.newmemberDao = daoBuilder.build("newmember", NewMemberDao.class);
    memberDao = daoBuilder.build("member", MemberDao.class);
    boardDao = daoBuilder.build("board", BoardDao.class);
    noticeDao = daoBuilder.build("notice", BoardDao.class);

    prepareMenu();
  }

  public void close() throws Exception {
    prompt.close();
    out.close();
    in.close();
    socket.close();
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

    try {
      out.writeUTF(new RequestEntity().command("quit").toJson());
    } catch (Exception e) {
      System.out.println("종료 오류!");
      e.printStackTrace();
    }
  }

  public void prepareMenu() {
    MenuGroup newmemberMenu = new MenuGroup("주차 관리 시스템");
    newmemberMenu.add(new Menu("회원가입", new NewMemberAddListener(newmemberDao)));
    newmemberMenu.add(new Menu("로그인", new NewMemberLoginListener(newmemberDao)));
    newmemberMenu.add(new Menu("회원정보 변경", new NewMemberUpdateListener(newmemberDao)));
    newmemberMenu.add(new Menu("회원정보 조회", new NewMemberListListener(newmemberDao)));
    newmemberMenu.add(new Menu("회원탈퇴", new NewMemberDeleteListener(newmemberDao)));
    mainMenu.add(newmemberMenu);

    Menu helloMenu = new Menu("안녕하세요.");
    helloMenu.addActionListener(new HeaderListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FooterListener());
    mainMenu.add(helloMenu);

  }


  public static void service(BreadcrumbPrompt prompt) {

    MenuGroup mainMenu = new MenuGroup("로비");

    MenuGroup memberMenu = new MenuGroup("주차관리");
    memberMenu.add(new Menu("차량 등록", new MemberAddListener(memberDao)));
    memberMenu.add(new Menu("차량 목록", new MemberListListener(memberDao)));
    memberMenu.add(new Menu("차량 조회", new MemberDetailListener(memberDao)));
    memberMenu.add(new Menu("정보 변경", new MemberUpdateListener(memberDao)));
    memberMenu.add(new Menu("차량 삭제", new MemberDeleteListener(memberDao)));
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