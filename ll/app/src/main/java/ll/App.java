
package ll;

import ll.handler.BoardHandler;
import ll.handler.Handler;
import ll.handler.MemberHandler;
import ll.util.ArrayList;
import ll.util.LinkedList;
import ll.util.MenuPrompt;

public class App {

  public static void main(String[] args) {

    MenuPrompt prompt = new MenuPrompt();
    prompt.appendBreadcrumb("메인", getMenu());

    // 기본 생성자를 이용해 Prompt 인스턴스를 준비한다.
    // => 기본 생성자는 Scanner를 키보드와 연결한다. OK


    Handler memberHandler = new MemberHandler(prompt, "주민", new ArrayList());
    Handler boardHandler = new BoardHandler(prompt, "독서록", new LinkedList());

    printTitle();

    prompt.printMenu();

    loop: while (true) {
      String menuNo = prompt.inputMenu();
      switch (menuNo) {
        case "0":
          break loop;
        case "1":
          memberHandler.execute();
          break;
        case "2":
          boardHandler.execute();
          break;
      }
    }
    prompt.close();
  }


  static String getMenu() {
    StringBuilder menu = new StringBuilder();
    menu.append("1. 주차관리 시스템\n");
    menu.append("2. 게시글\n");
    menu.append("0. 종료\n");
    return menu.toString();
  }

  static void printTitle() {
    System.out.println("--------------------------");
    System.out.println("     주차 관리 시스템     ");
    System.out.println("--------------------------");
  }
}
