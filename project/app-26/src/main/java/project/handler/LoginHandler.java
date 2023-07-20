package project.handler;

import java.util.ArrayList;
import java.util.LinkedList;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.util.Menu;
import project.util.MenuGroup;
import project.vo.Board;
import project.vo.Member;

public class LoginHandler implements ActionListener {

  ArrayList<Member> memberList = new ArrayList<>();
  LinkedList<Board> boardList = new LinkedList<>();
  LinkedList<Board> noticeList = new LinkedList<>();

  MenuGroup mainMenu = new MenuGroup("로비");

  @Override
  public void service(BreadcrumbPrompt prompt) {

    MenuGroup memberMenu = new MenuGroup("주차관리");
    memberMenu.add(new Menu("차량 등록", new MemberAddListener(memberList)));
    memberMenu.add(new Menu("차량 목록", new MemberListListener(memberList)));
    memberMenu.add(new Menu("차량 조회", new MemberDetailListener(memberList)));
    memberMenu.add(new Menu("정보 변경", new MemberUpdateListener(memberList)));
    memberMenu.add(new Menu("차량 삭제", new MemberDeleteListener(memberList)));
    memberMenu.add(new Menu("차량 입차 기록", new MemberEntryListener(memberList)));
    memberMenu.add(new Menu("차량 출차 기록", new MemberExitListener(memberList)));
    mainMenu.add(memberMenu);

    MenuGroup boardMenu = new MenuGroup("민원사항");
    boardMenu.add(new Menu("민원사항 등록", new BoardAddListener(boardList)));
    boardMenu.add(new Menu("민원사항 목록", new BoardListListener(boardList)));
    boardMenu.add(new Menu("민원사항 조회", new BoardDetailListener(boardList)));
    boardMenu.add(new Menu("민원사항 변경", new BoardUpdateListener(boardList)));
    boardMenu.add(new Menu("민원사항 삭제", new BoardDeleteListener(boardList)));
    mainMenu.add(boardMenu);

    MenuGroup noticeMenu = new MenuGroup("공지사항");
    noticeMenu.add(new Menu("공지사항 등록", new BoardAddListener(noticeList)));
    noticeMenu.add(new Menu("공지사항 목록", new BoardListListener(noticeList)));
    noticeMenu.add(new Menu("공지사항 조회", new BoardDetailListener(noticeList)));
    noticeMenu.add(new Menu("공지사항 변경", new BoardUpdateListener(noticeList)));
    noticeMenu.add(new Menu("공지사항 삭제", new BoardDeleteListener(noticeList)));
    mainMenu.add(noticeMenu);


    mainMenu.execute(prompt);
  }

}
