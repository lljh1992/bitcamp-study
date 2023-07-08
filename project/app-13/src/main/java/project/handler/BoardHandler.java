package project.handler;

import util.Prompt;
import vo.Board;

public class BoardHandler {

  static final int SIZE = 100;
  static Board[] boards = new Board[SIZE];
  static int length = 0;


  static void execute() {

    printMainMenu();

    while (true) {
      String menuNo = Prompt.inputString("메인> ");
      if (menuNo.equals("0")) {
        LoginHandler.printLoginMenu();
        break;
      } else if (menuNo.equals("menu")) {
        printMainMenu();
      } else if (menuNo.equals("1")) {
        BoardHandler.inputBoard();
      } else if (menuNo.equals("2")) {
        BoardHandler.pirntBoards();
      } else if (menuNo.equals("3")) {
        BoardHandler.viewBoard();
      } else if (menuNo.equals("4")) {
        BoardHandler.updateBoard();
      } else if (menuNo.equals("5")) {
        BoardHandler.deleteBoard();
      } else {
      }
    }
  }

  public static void printMainMenu() {
    System.out.println("1. 민원사항 등록");
    System.out.println("2. 민원사항 목록");
    System.out.println("3. 민원사항 조회");
    System.out.println("4. 민원사항 변경");
    System.out.println("5. 민원사항 삭제");
    System.out.println("0. 이전 메뉴");
  }



  public static void inputBoard() {
    if (!available()) {
      System.out.println("더이상 입력할 수 없습니다.");
      return;
    }

    Board board = new Board();
    board.setTitle(Prompt.inputString("제목? "));
    board.setContent(Prompt.inputString("내용? "));
    board.setWriter(Prompt.inputString("작성자? "));
    board.setPassword(Prompt.inputString("암호? "));

    boards[length++] = board;
  }

  public static void pirntBoards() {
    System.out.println("----------------------------------------------------------------");
    System.out.println("번호 | 제목 | 작성자 | 조회수 | 등록일");
    System.out.println("----------------------------------------------------------------");

    for (int i = 0; i < length; i++) {
      Board board = boards[i];
      System.out.printf("%d, %s, %s, %d, %tY-%5$tm-%5$td\n", board.getNo(), board.getTitle(),
          board.getWriter(), board.getViewCount(), board.getCreatedDate());
    }
  }

  public static void viewBoard() {
    String boardNo = Prompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      Board board = boards[i];
      if (board.getNo() == Integer.parseInt(boardNo)) {
        System.out.printf("제목: %s\n", board.getTitle());
        System.out.printf("내용: %s\n", board.getContent());
        System.out.printf("작성자: %s\n", board.getWriter());
        System.out.printf("조회수: %s\n", board.getViewCount());
        System.out.printf("등록일: %tY-%1$tm-%1$td\n", board.getCreatedDate());
        board.setViewCount(board.getViewCount() + 1);
        return;
      }
    }
    System.out.println("해당 번호의 글이 없습니다!");
  }

  public static void updateBoard() {
    String boardNo = Prompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      Board board = boards[i];
      if (board.getNo() == Integer.parseInt(boardNo)) {
        if (!Prompt.inputString("암호? ").equals(board.getPassword())) {
          System.out.println("암호가 일치하지 않습니다!");
          return;
        }
        board.setTitle(Prompt.inputString("제목(%s)? ", board.getTitle()));
        board.setContent(Prompt.inputString("내용(%s)? ", board.getContent()));
        return;
      }
    }
    System.out.println("해당 번호의 글이 없습니다!");
  }

  public static void deleteBoard() {
    int deletedIndex = indexOf(Prompt.inputInt("번호? "));
    if (deletedIndex == -1) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    for (int i = deletedIndex; i < length - 1; i++) {
      boards[i] = boards[i + 1];
    }
    boards[--length] = null;
  }

  private static int indexOf(int boardNo) {
    for (int i = 0; i < length; i++) {
      Board board = boards[i];
      if (board.getNo() == boardNo) {
        return i;
      }
    }
    return -1;
  }

  public static boolean available() {
    return length < SIZE;
  }
}
