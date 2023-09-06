package project.controller;

import util.ArrayList;
import util.LinkedList;
import util.Prompt;

public class LoginHandler implements Handler {

  private Prompt prompt;
  private String title;

  public LoginHandler(Prompt prompt, String title) {
    this.prompt = prompt;
    this.title = title;
  }

  @Override
  public void execute() {

    MemberHandler memberHandler = new MemberHandler(prompt, "차량관리", new ArrayList());
    BoardHandler boardHandler = new BoardHandler(prompt, "민원사항", new LinkedList());
    BoardHandler noticeHandler = new BoardHandler(prompt, "공지사항", new LinkedList());

    printLoginMenu();

    while (true) {
      String menuNo = prompt.inputString("%s > ", this.title);
      if (menuNo.equals("0")) {
        NewMemberHandler.printMainMenu();
        break;
      } else if (menuNo.equals("menu")) {
        printLoginMenu();
      } else if (menuNo.equals("1")) {
        memberHandler.execute();
      } else if (menuNo.equals("2")) {
        boardHandler.execute();
      } else if (menuNo.equals("3")) {
        noticeHandler.execute();
      }
    }
  }

  static void printLoginMenu() {
    System.out.println("1. 차량관리");
    System.out.println("2. 민원사항");
    System.out.println("3. 공지사항");
    System.out.println("0. 로그아웃");
  }

}
