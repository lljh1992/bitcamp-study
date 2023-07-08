/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package project;

import project.handler.NewMemberHandler;
import util.Prompt;

public class App {

  public static void main(String[] args) {

    printTitle();

    NewMemberHandler.printMainMenu();

    while (true) {
      String menuNo = Prompt.inputString("해당 번호를 입력하세요 > ");
      if (menuNo.equals("0")) {
        break;
      } else if (menuNo.equals("menu")) {
        NewMemberHandler.printMainMenu();
      } else if (menuNo.equals("1")) {  // 회원가입
        NewMemberHandler.inputNewMember();
      } else if (menuNo.equals("2")) {  // 로그인
        NewMemberHandler.login();
      } else if (menuNo.equals("3")) {
        NewMemberHandler.updateNewMember(); // 회원정보 변경
      } else if (menuNo.equals("4")) {
        NewMemberHandler.pirntBoards(); // 회원 정보 조회
      }
    }
    Prompt.close();
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