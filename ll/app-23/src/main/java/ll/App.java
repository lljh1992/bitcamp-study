
package ll;

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
import ll.util.ArrayList;
import ll.util.BreadcrumbPrompt;
import ll.util.LinkedList;
import ll.util.Menu;
import ll.util.MenuGroup;

public class App {

  public static void main(String[] args) {

    ArrayList memberList = new ArrayList();
    LinkedList boardList = new LinkedList();

    BreadcrumbPrompt prompt = new BreadcrumbPrompt();

    MenuGroup mainMenu = new MenuGroup("메인");

    MenuGroup memberMenu = new MenuGroup("주차관리");
    memberMenu.add(new Menu("차량등록", new MemberAddListener(memberList)));
    memberMenu.add(new Menu("차량목록", new MemberListListener(memberList)));
    memberMenu.add(new Menu("차량조회", new MemberDetailListener(memberList)));
    memberMenu.add(new Menu("등록변경", new MemberUpdateListener(memberList)));
    memberMenu.add(new Menu("등록삭제", new MemberDeleteListener(memberList)));
    mainMenu.add(memberMenu);

    MenuGroup boardMenu = new MenuGroup("게시글");
    boardMenu.add(new Menu("게시글등록", new BoardAddListener(boardList)));
    boardMenu.add(new Menu("게시글목록", new BoardListListener(boardList)));
    boardMenu.add(new Menu("게시글조회", new BoardDetailListener(boardList)));
    boardMenu.add(new Menu("게시글변경", new BoardUpdateListener(boardList)));
    boardMenu.add(new Menu("게시글삭제", new BoardDeleteListener(boardList)));
    mainMenu.add(boardMenu);

    Menu helloMenu = new Menu("안녕!");
    helloMenu.addActionListener(new HeaderListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FooterListener());
    mainMenu.add(helloMenu);

    printTitle();

    mainMenu.execute(prompt);

    prompt.close();
  }

  static void printTitle() {
    System.out.println("--------------------------");
    System.out.println("     주차 관리 시스템     ");
    System.out.println("--------------------------");
  }
}
