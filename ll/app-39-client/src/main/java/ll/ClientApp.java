
package ll;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import ll.dao.BoardDao;
import ll.dao.DaoBuilder;
import ll.dao.MemberDao;
import ll.handler.BoardAddListener;
import ll.handler.BoardDeleteListener;
import ll.handler.BoardDetailListener;
import ll.handler.BoardListListener;
import ll.handler.BoardUpdateListener;
import ll.handler.FooterListener;
import ll.handler.HeaderListener;
import ll.handler.HelloListener;
import ll.handler.MemberAddListener;
import ll.handler.MemberDeleteListener;
import ll.handler.MemberDetailListener;
import ll.handler.MemberListListener;
import ll.handler.MemberUpdateListener;
import ll.net.RequestEntity;
import ll.util.BreadcrumbPrompt;
import ll.util.Menu;
import ll.util.MenuGroup;

public class ClientApp {

  Socket socket;
  DataOutputStream out;
  DataInputStream in;

  BoardDao boardDao;
  MemberDao memberDao;

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");

  public ClientApp(String ip, int port) throws Exception {

    this.socket = new Socket(ip, port);
    this.out = new DataOutputStream(socket.getOutputStream());
    this.in = new DataInputStream(socket.getInputStream());

    DaoBuilder daoBuilder = new DaoBuilder(in, out);

    this.memberDao = daoBuilder.build("member", MemberDao.class);
    this.boardDao = daoBuilder.build("board", BoardDao.class);

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
      System.out.println("실행 예) java ... bitcamp.myapp.ClientApp 서버주소 포트번호");
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

    MenuGroup memberMenu = new MenuGroup("주차관리");
    memberMenu.add(new Menu("차량등록", new MemberAddListener(memberDao)));
    memberMenu.add(new Menu("차량목록", new MemberListListener(memberDao)));
    memberMenu.add(new Menu("차량조회", new MemberDetailListener(memberDao)));
    memberMenu.add(new Menu("등록변경", new MemberUpdateListener(memberDao)));
    memberMenu.add(new Menu("등록삭제", new MemberDeleteListener(memberDao)));
    mainMenu.add(memberMenu);

    MenuGroup boardMenu = new MenuGroup("게시글");
    boardMenu.add(new Menu("게시글등록", new BoardAddListener(boardDao)));
    boardMenu.add(new Menu("게시글목록", new BoardListListener(boardDao)));
    boardMenu.add(new Menu("게시글조회", new BoardDetailListener(boardDao)));
    boardMenu.add(new Menu("게시글변경", new BoardUpdateListener(boardDao)));
    boardMenu.add(new Menu("게시글삭제", new BoardDeleteListener(boardDao)));
    mainMenu.add(boardMenu);

    Menu helloMenu = new Menu("안녕!");
    helloMenu.addActionListener(new HeaderListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FooterListener());
    mainMenu.add(helloMenu);

  }



}

