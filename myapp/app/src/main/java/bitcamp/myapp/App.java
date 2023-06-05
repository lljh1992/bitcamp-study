package bitcamp.myapp;

import bitcamp.myapp.handler.MemberHandler;
import bitcamp.util.Prompt;

// 코드 본문에서 사용할 클래스가 어떤 패키지의 클래스인지 지정한다.

public class App {
    public static void main(String[] args) {

    printTitle();

    while (MemberHandler.available()) {
      MemberHandler.inputMember();
      if (!promptContinue()) {
        break;
      }
    }

    MemberHandler.printMembers();

    Prompt.close();
  }

  static void printTitle() {
    System.out.println("나의 목록 관리 시스템");
    System.out.println("----------------------------------");
  }
 
  static boolean promptContinue() {
    System.out.print("계속 하시겠습니까?(Y/n) ");
    String response = Prompt.scanner.nextLine();
    if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
      return false;
    }
    return true;
  }
   
}