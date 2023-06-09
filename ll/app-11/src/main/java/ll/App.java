
package ll;

import ll.handler.MemberHandler;
import ll.util.Prompt;

public class App {

    public static void main(String[] args) {

        printTitle();

        printMenu();

        while (true) {
          String menuNo = Prompt.inputString("메인> ");
          if (menuNo.equals("6")) {
            break;
          } else if (menuNo.equals("menu")) {
            printMenu();
          } else if (menuNo.equals("1")) {
            MemberHandler.inputMember();
          } else if (menuNo.equals("2")) {
            MemberHandler.pirntMembers();
          } else if (menuNo.equals("3")) {
            MemberHandler.viewMember();
          } else if (menuNo.equals("4")) {
            MemberHandler.updateMember();
          } else if (menuNo.equals("5")) {
            MemberHandler.deleteMember();
          } else {
            System.out.println(menuNo);
          }
        }

        Prompt.close();
    }

    static void printMenu() {
      System.out.println("1. 차량 등록");
      System.out.println("2. 차량 목록");
      System.out.println("3. 차량 조회");
      System.out.println("4. 정보 변경");
      System.out.println("5. 차량 삭제");
      System.out.println("6. 종료");

    }


    static void printTitle() {
        System.out.println("--------------------------");
        System.out.println("     주차 관리 시스템     ");
        System.out.println("--------------------------");
    }

    static boolean promptContinue() {
        String response = Prompt.inputString("등록을 계속 하시겠습니까?(Y/n) ");
        if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
            return false;
        }
        return true;
    }
}
