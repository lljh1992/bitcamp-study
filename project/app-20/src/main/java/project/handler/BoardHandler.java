package project.controller;

import util.LinkedList;
import util.Prompt;
import vo.Board;

public class BoardHandler implements Handler {

  private LinkedList list = new LinkedList();
  private Prompt prompt;
  private String title;

  public BoardHandler(Prompt prompt, String title) {
    this.prompt = prompt;
    this.title = title;
  }

  @Override
  public void execute() {

    // BoardHandler boardHandler = new BoardHandler(prompt, title);
    // BoardHandler readingHandler = new BoardHandler(prompt, title);

    printMainMenu();

    while (true) {
      String menuNo = prompt.inputString("%s > ", this.title);
      if (menuNo.equals("0")) {
        LoginHandler.printLoginMenu();
        break;
      } else if (menuNo.equals("menu")) {
        printMainMenu();
      } else if (menuNo.equals("1")) {
        this.inputBoard();
      } else if (menuNo.equals("2")) {
        this.pirntBoards();
      } else if (menuNo.equals("3")) {
        this.viewBoard();
      } else if (menuNo.equals("4")) {
        this.updateBoard();
      } else if (menuNo.equals("5")) {
        this.deleteBoard();
      } else {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }
  }

  public static void printMainMenu() {
    System.out.println("1. 등록");
    System.out.println("2. 목록");
    System.out.println("3. 조회");
    System.out.println("4. 변경");
    System.out.println("5. 삭제");
    System.out.println("0. 이전 메뉴");
  }

  private void inputBoard() {
    Board board = new Board();
    board.setTitle(this.prompt.inputString("제목? "));
    board.setContent(this.prompt.inputString("내용? "));
    board.setWriter(this.prompt.inputString("작성자? "));
    board.setPassword(this.prompt.inputString("암호? "));

    this.list.add(board);
  }

  private void pirntBoards() {
    System.out.println("----------------------------------------------------------------");
    System.out.println("번호 | 제목 | 작성자 | 조회수 | 등록일");
    System.out.println("----------------------------------------------------------------");

    Object[] arr = this.list.getList();
    for (Object obj : arr) {
      Board board = (Board) obj;
      System.out.printf("%d, %s, %s, %d, %tY-%5$tm-%5$td\n", board.getNo(), board.getTitle(),
          board.getWriter(), board.getViewCount(), board.getCreatedDate());
    }
  }

  private void viewBoard() {
    int boardNo = this.prompt.inputInt("번호? ");

    Board board = (Board) this.list.retrieve(new Board(boardNo));
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다!");
      return;
    }

    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("내용: %s\n", board.getContent());
    System.out.printf("작성자: %s\n", board.getWriter());
    System.out.printf("조회수: %s\n", board.getViewCount());
    System.out.printf("등록일: %tY-%1$tm-%1$td\n", board.getCreatedDate());
    board.setViewCount(board.getViewCount() + 1);
  }

  private void updateBoard() {
    int boardNo = this.prompt.inputInt("번호? ");

    Board board = (Board) this.list.retrieve(new Board(boardNo));
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다!");
      return;
    }

    if (!this.prompt.inputString("암호? ").equals(board.getPassword())) {
      System.out.println("암호가 일치하지 않습니다!");
      return;
    }

    board.setTitle(this.prompt.inputString("제목(%s) > ", board.getTitle()));
    board.setContent(this.prompt.inputString("내용(%s) > ", board.getContent()));
  }

  private void deleteBoard() {
    if (!this.list.remove(new Board(this.prompt.inputInt("번호? ")))) {
      System.out.println("해당 번호의 게시글이 없습니다!");
    }
  }

}
