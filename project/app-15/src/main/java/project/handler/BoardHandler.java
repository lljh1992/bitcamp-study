package project.handler;

import util.Prompt;
import vo.Board;

public class BoardHandler {

  private static final int SIZE = 100;

  private Prompt prompt;
  static Board[] boards = new Board[SIZE];
  static int length = 0;


  public void execute() {
    BoardHandler boardHandler = new BoardHandler(prompt);
    BoardHandler readingHandler = new BoardHandler(prompt);

    printMainMenu();

    while (true) {
      String menuNo = Prompt.inputString("메인> ");
      if (menuNo.equals("0")) {
        LoginHandler.printLoginMenu();
        break;
      } else if (menuNo.equals("menu")) {
        printMainMenu();
      } else if (menuNo.equals("1")) {
        boardHandler.inputBoard();
      } else if (menuNo.equals("2")) {
        boardHandler.pirntBoards();
      } else if (menuNo.equals("3")) {
        boardHandler.viewBoard();
      } else if (menuNo.equals("4")) {
        boardHandler.updateBoard();
      } else if (menuNo.equals("5")) {
        boardHandler.deleteBoard();
      } else if (menuNo.equals("6")) {
        readingHandler.inputBoard();
      } else if (menuNo.equals("7")) {
        readingHandler.pirntBoards();
      } else if (menuNo.equals("8")) {
        readingHandler.viewBoard();
      } else if (menuNo.equals("9")) {
        readingHandler.updateBoard();
      } else if (menuNo.equals("10")) {
        readingHandler.deleteBoard();
      } else {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }
  }


  public BoardHandler(Prompt prompt) {
    this.prompt = prompt;
  }

  public static void printMainMenu() {
    System.out.println("1. 민원사항 등록");
    System.out.println("2. 민원사항 목록");
    System.out.println("3. 민원사항 조회");
    System.out.println("4. 민원사항 변경");
    System.out.println("5. 민원사항 삭제");
    System.out.println("6. 공지사항 등록");
    System.out.println("7. 공지사항 목록");
    System.out.println("8. 공지사항 조회");
    System.out.println("9. 공지사항 변경");
    System.out.println("10. 공지사항 삭제");
    System.out.println("0. 이전 메뉴");
  }



  public void inputBoard() {
    if (!this.available()) {
      System.out.println("더이상 입력할 수 없습니다.");
      return;
    }

    Board board = new Board();
    board.setTitle(this.prompt.inputString("제목? "));
    board.setContent(this.prompt.inputString("내용? "));
    board.setWriter(this.prompt.inputString("작성자? "));
    board.setPassword(this.prompt.inputString("암호? "));

    this.boards[this.length++] = board;
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

  public void viewBoard() {
    String boardNo = this.prompt.inputString("번호? ");
    for (int i = 0; i < this.length; i++) {
      Board board = this.boards[i];
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

  public void updateBoard() {
    String boardNo = this.prompt.inputString("번호? ");
    for (int i = 0; i < this.length; i++) {
      Board board = boards[i];
      if (board.getNo() == Integer.parseInt(boardNo)) {
        if (!this.prompt.inputString("암호? ").equals(board.getPassword())) {
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

  public void deleteBoard() {
    int deletedIndex = indexOf(this.prompt.inputInt("번호? "));
    if (deletedIndex == -1) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    for (int i = deletedIndex; i < this.length - 1; i++) {
      this.boards[i] = this.boards[i + 1];
    }
    this.boards[--this.length] = null;
  }

  private int indexOf(int boardNo) {
    for (int i = 0; i < this.length; i++) {
      Board board = this.boards[i];
      if (board.getNo() == boardNo) {
        return i;
      }
    }
    return -1;
  }

  private boolean available() {
    return this.length < SIZE;
  }
}
