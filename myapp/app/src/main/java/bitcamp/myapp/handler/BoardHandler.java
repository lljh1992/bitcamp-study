package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;

public class BoardHandler {

  static final int MAX_SIZE = 100;
  static Board[] boards = new Board[MAX_SIZE];
  static int length = 0;

  public static void inputBoard() {
    if (!available()) {
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }

    Board board = new Board();
    board.setTitle(Prompt.inputString("제목? "));
    board.setContent(Prompt.inputString("내용? "));
    board.setWriter(Prompt.inputString("작성자? "));
    board.setPassword(Prompt.inputString("암호? "));
    boards[length++] = board;
  }

  public static void printBoards() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 제목, 작성자, 조회수, 등록일");
    System.out.println("---------------------------------------");

    for (int i = 0; i < length; i++) {
      Board board = boards[i];

      System.out.printf("%d, %s, %s, %d, %tY-%5$tm-%5$td\n", board.getNo(), board.getTitle(),
          board.getWriter(), board.getViewCount(), board.getCreatedDate());
    }
  }

  public static void viewBoard() {
    String memberNo = Prompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      Board board = boards[i];
      if (board.getNo() == Integer.parseInt(memberNo)) {
        System.out.printf("제목: %s\n", board.getTitle());
        System.out.printf("작성자: %s\n", board.getWriter());
        System.out.printf("내용: %s\n", board.getContent());
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
        // 글의 번호를 입력하고, 암호가 일치하면 제목과 내용을 수정할 수 있다. 그렇지 않으면 다시 메뉴로 돌아가게.
        String password = Prompt.inputString("암호? ");
        if (board.getPassword().equals(password)) {
          System.out.printf("제목(%s)? ", board.getTitle());
          board.setTitle(Prompt.inputString(""));
          System.out.printf("내용(%s)? ", board.getContent());
          board.setContent(Prompt.inputString(""));
          System.out.printf("새암호? ");
          board.setPassword(Prompt.inputString(""));
          return;
        } else {
          System.out.println("비밀번호가 틀렸습니다.");
        }
      } else {
        System.out.println("해당 번호의 회원이 없습니다!");
      }
    }
  }

  // public static void updateBoard() {
  // String memberNo = Prompt.inputString("번호? ");
  // for (int i = 0; i < length; i++) {
  // Board m = boards[i];
  // if (m.getNo() == Integer.parseInt(memberNo)) {
  // System.out.printf("이름(%s)? ", m.getName());
  // m.setName(Prompt.inputString(""));
  // System.out.printf("이메일(%s)? ", m.getEmail());
  // m.setEmail(Prompt.inputString(""));
  // System.out.printf("새암호? ");
  // m.setPassword(Prompt.inputString(""));
  // m.setGender(inputGender(m.getGender()));
  // return;
  // }
  // }
  // System.out.println("해당 번호의 회원이 없습니다!");
  // }


  public static void deleteBoard() {
    int boardNo = Prompt.inputInt("번호? ");

    int deletedIndex = indexOf(boardNo);
    if (deletedIndex == -1) {
      System.out.println("해당 번호의 글이 없습니다!");
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

  private static boolean available() {
    return length < MAX_SIZE;
  }
}
