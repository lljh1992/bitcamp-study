
package ll;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
import ll.util.BreadcrumbPrompt;
import ll.util.Menu;
import ll.util.MenuGroup;
import ll.vo.Board;
import ll.vo.Member;

public class App {

  ArrayList<Member> memberList = new ArrayList<>();
  LinkedList<Board> boardList = new LinkedList<>();

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");

  public App() {

    prepareMenu();
  }

  public static void main(String[] args) {

    new App().execute();
  }

  static void printTitle() {
    System.out.println("--------------------------");
    System.out.println("     주차 관리 시스템     ");
    System.out.println("--------------------------");
  }

  public void execute() {

    printTitle();

    loadData();
    mainMenu.execute(prompt);
    saveData();

    prompt.close();
  }

  private void loadData() {
    loadMember("member.data2", memberList);
    loadBoard("board.data2", boardList);
  }

  private void saveData() {
    saveMember("member.csv", memberList);
    saveBoard("board.csv", boardList);
  }

  public void prepareMenu() {

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

  }

  private void loadMember(String filename, List<Member> list) {
    try {
      FileInputStream in0 = new FileInputStream(filename);
      BufferedInputStream in1 = new BufferedInputStream(in0);
      ObjectInputStream in = new ObjectInputStream(in1);

      int size = in.readShort();

      for (int i = 0; i < size; i++) {
        list.add((Member) in.readObject());
      }

      if (list.size() > 0) {
        // 데이터를 로딩한 이후에 추가할 회원의 번호를 설정한다.
        Member.userID = memberList.get(memberList.size() - 1).getNo() + 1;

      }

      in.close();
    } catch (Exception e) {
      System.out.println("차량 정보를 읽는 중 오류 발생!");
    }
  }

  private void loadBoard(String filename, List<Board> list) {
    try {
      FileInputStream in0 = new FileInputStream(filename);
      BufferedInputStream in1 = new BufferedInputStream(in0);
      ObjectInputStream in = new ObjectInputStream(in1);

      int size = in.readShort();

      for (int i = 0; i < size; i++) {
        list.add((Board) in.readObject());
      }

      if (list.size() > 0) {
        // 보드로 여러개의 데이터를 받을때 사
        Board.boardNo = Math.max(Board.boardNo, list.get(list.size() - 1).getNo() + 1);
      }

      in.close();

    } catch (Exception e) {
      System.out.println("게시물 정보를 읽는 중 오류 발생!");
    }
  }

  private void saveMember(String filename, List<Member> list) {
    try {
      FileOutputStream out0 = new FileOutputStream(filename);
      BufferedOutputStream out1 = new BufferedOutputStream(out0);
      ObjectOutputStream out = new ObjectOutputStream(out1);

      out.writeShort(memberList.size());

      for (Member member : memberList) {
        out.writeObject(member);
      }

      out.close();
    } catch (Exception e) {
      System.out.println("차량 정보 저장하는 중 오류 발생!");
    }
  }

  private void saveBoard(String filename, List<Board> list) {
    try {
      FileOutputStream out0 = new FileOutputStream(filename);
      BufferedOutputStream out1 = new BufferedOutputStream(out0);
      ObjectOutputStream out = new ObjectOutputStream(out1);

      out.writeShort(boardList.size());

      for (Board board : boardList) {
        out.writeObject(board);
      }
      out.close();

    } catch (Exception e) {
      System.out.println(filename + "파일을 저장하는 중 오류 발생!");
    }
  }

}

