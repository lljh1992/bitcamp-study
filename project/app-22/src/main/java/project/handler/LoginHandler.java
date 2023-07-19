package project.handler;

import project.util.ArrayList;
import project.util.LinkedList;
import project.util.List;
import project.util.MenuPrompt;

public class LoginHandler implements Handler {

  private List list;
  private MenuPrompt prompt;
  private String title;

  public LoginHandler(MenuPrompt prompt, String title, List list) {
    this.prompt = prompt;
    this.title = title;
  }



  @Override
  public void execute() {

    prompt.appendBreadcrumb(this.title, getMenu());

    Handler memberHandler = new MemberHandler(prompt, "차량관리", new ArrayList());
    Handler boardHandler = new BoardHandler(prompt, "민원사항", new LinkedList());
    Handler noticeHandler = new BoardHandler(prompt, "공지사항", new LinkedList());

    prompt.printMenu();


    while (true) {
      String menuNo = prompt.inputMenu();
      switch (menuNo) {
        case "0":
          prompt.removeBreadcrumb();
          return;
        case "1":
          memberHandler.execute();
          break;
        case "2":
          boardHandler.execute();
          break;
        case "3":
          noticeHandler.execute();
          break;
      }
    }

    // while (true) {
    // String menuNo = prompt.inputString("%s > ", this.title);
    // if (menuNo.equals("0")) {
    // NewMemberHandler.getMenu();
    // break;
    // } else if (menuNo.equals("menu")) {
    // getMenu();
    // } else if (menuNo.equals("1")) {
    // memberHandler.execute();
    // } else if (menuNo.equals("2")) {
    // boardHandler.execute();
    // } else if (menuNo.equals("3")) {
    // noticeHandler.execute();
    // }
    // }
  }

  private static String getMenu() {
    StringBuilder menu = new StringBuilder();
    menu.append("1. 차량관리\n");
    menu.append("2. 민원사항\n");
    menu.append("3. 공지사항\n");
    menu.append("0. 로그아웃");
    return menu.toString();
  }

}
