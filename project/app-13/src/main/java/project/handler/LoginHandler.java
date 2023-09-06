package project.controller;

import util.Prompt;
import vo.NewMember;

public class LoginHandler {
  static final int NEWSIZE = 100;
  static NewMember[] nmember = new NewMember[NEWSIZE];

  static int newlength = 0;

  static void execute() {

    printLoginMenu();

    while (true) {
      String menuNo = Prompt.inputString("해당 번호를 입력하세요 > ");
      if (menuNo.equals("0")) {
        NewMemberHandler.printMainMenu();
        break;
      } else if (menuNo.equals("menu")) {
        LoginHandler.printLoginMenu();
      } else if (menuNo.equals("1")) {
        MemberHandler.execute();
      } else if (menuNo.equals("2")) {
        BoardHandler.execute();
      }
      //      else if (menuNo.equals("3")) {
      //        NewMemberHandler.updateNewMember();
      //      }
    }
  }

  static void printLoginMenu() {
    System.out.println("1. 차량관리");
    System.out.println("2. 민원사항");
    //    System.out.println("3. 공지사항");
    System.out.println("0. 로그아웃");
  }

}
