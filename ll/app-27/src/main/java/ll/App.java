
package ll;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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
    loadMember();
    loadBoard(boardList);
  }

  private void saveData() {
    saveMember();
    saveBoard(boardList);
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

  private void loadMember() {
    try {
      FileInputStream in = new FileInputStream("member.data");
      int size = in.read() << 8;
      size |= in.read();

      byte[] buf = new byte[1000];

      for (int i = 0; i < size; i++) {
        Member member = new Member();
        member.setNo(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        int length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        member.setA(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        member.setB(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        member.setName(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        member.setPhonenumber(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        member.setCarnumber(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        member.setVehicleOwnership(new String(buf, 0, length, "UTF-8"));

        member.setType((char) (in.read() << 8 | in.read()));

        memberList.add(member);
      }

      Member.userID = memberList.get(memberList.size() - 1).getNo() + 1;

      in.close();
    } catch (Exception e) {
      System.out.println("차량 정보를 읽는 중 오류 발생!");
    }
  }

  private void loadBoard(List<Board> list) {
    try {
      FileInputStream in = new FileInputStream("board.data");
      int size = in.read() << 8;
      size |= in.read();

      byte[] buf = new byte[1000];

      for (int i = 0; i < size; i++) {
        Board board = new Board();
        board.setNo(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        int length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        board.setTitle(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        board.setContent(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        board.setWriter(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        board.setPassword(new String(buf, 0, length, "UTF-8"));

        board.setViewCount(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        board.setCreatedDate((long) in.read() << 56 | (long) in.read() << 48
            | (long) in.read() << 40 | (long) in.read() << 32 | (long) in.read() << 24
            | (long) in.read() << 16 | (long) in.read() << 8 | in.read());

        boardList.add(board);
      }

      // 보드로 여러개의 데이터를 받을때 사
      Board.boardNo = Math.max(Board.boardNo, list.get(list.size() - 1).getNo() + 1);

      in.close();

    } catch (Exception e) {
      System.out.println("게시물 정보를 읽는 중 오류 발생!");
    }
  }

  private void saveMember() {
    try {
      FileOutputStream out = new FileOutputStream("member.data");

      // 저장할 데이터의 개수를 먼저 출력한다.
      int size = memberList.size();
      out.write(size >> 8);
      out.write(size);

      for (Member member : memberList) {
        int no = member.getNo();
        out.write(no >> 24);
        out.write(no >> 16);
        out.write(no >> 8);
        out.write(no);

        byte[] bytes = member.getA().getBytes("UTF-8");
        // 출력할 바이트의 개수를 2바이트로 표시한다.
        out.write(bytes.length >> 8);
        out.write(bytes.length);

        // 문자열 바이트를 출력한다.
        out.write(bytes);

        bytes = member.getB().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = member.getName().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = member.getPhonenumber().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = member.getCarnumber().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = member.getVehicleOwnership().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        char type = member.getType();
        out.write(type >> 8);
        out.write(type);
      }
      out.close();
    } catch (Exception e) {
      System.out.println("차량 정보를 저장하는 중 오류 발생!");
    }
  }

  private void saveBoard(List<Board> list) {
    try {
      FileOutputStream out = new FileOutputStream("board.data");

      // 저장할 데이터의 개수를 먼저 출력한다.
      int size = boardList.size();
      out.write(size >> 8);
      out.write(size);

      for (Board board : boardList) {
        int no = board.getNo();
        out.write(no >> 24);
        out.write(no >> 16);
        out.write(no >> 8);
        out.write(no);

        byte[] bytes = board.getTitle().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = board.getContent().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = board.getWriter().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = board.getPassword().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        int viewCount = board.getViewCount();
        out.write(viewCount >> 24);
        out.write(viewCount >> 16);
        out.write(viewCount >> 8);
        out.write(viewCount);

        long createdDate = board.getCreatedDate();
        out.write((int) (createdDate >> 56));
        out.write((int) (createdDate >> 48));
        out.write((int) (createdDate >> 40));
        out.write((int) (createdDate >> 32));
        out.write((int) (createdDate >> 24));
        out.write((int) (createdDate >> 16));
        out.write((int) (createdDate >> 8));
        out.write((int) (createdDate));

      }
      out.close();

    } catch (Exception e) {
      System.out.println("게시물 정보를 저장하는 중 오류 발생!");
    }
  }

}

