
package ll;

import ll.handler.BoardHandler;
import ll.handler.MemberHandler;
import ll.util.Prompt;

public class App {

  public static void main(String[] args) {

    // 기본 생성자를 이용해 Prompt 인스턴스를 준비한다.
    // => 기본 생성자는 Scanner를 키보드와 연결한다. OK
    Prompt prompt = new Prompt();

    MemberHandler memberHandler = new MemberHandler(prompt, "주민");
    BoardHandler boardHandler = new BoardHandler(prompt, "독서록");

    printTitle();

    printMenu();

    while (true) {
      String menuNo = prompt.inputString("메인> ");
      if (menuNo.equals("0")) {
        break;
      } else if (menuNo.equals("menu")) {
        printMenu();
      } else if (menuNo.equals("1")) {
        memberHandler.execute();
      } else if (menuNo.equals("2")) {
        boardHandler.service();
      } else {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }

    prompt.close();
  }

  static void printMenu() {
    System.out.println("1. 주차관리 시스템");
    System.out.println("2. 게시글");
    System.out.println("0. 종료");
  }

  static void printTitle() {
    System.out.println("--------------------------");
    System.out.println("     주차 관리 시스템     ");
    System.out.println("--------------------------");
  }
}
