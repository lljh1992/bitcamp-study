package project.handler;

import util.Prompt;

public class LoginHandler implements Handler {

  private Prompt prompt;
  private String title;

  public LoginHandler(Prompt prompt, String title) {
    this.prompt = prompt;
    this.title = title;
  }

  @Override
  public  void execute() {
    MemberHandler memberHandler = new MemberHandler(prompt, title);
    BoardHandler boardHandler = new BoardHandler(prompt, title);
    BoardHandler noticeHandler = new BoardHandler(prompt, title);

    printLoginMenu();

    while (true) {
      String menuNo = Prompt.inputString("%s > ", this.title);
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
