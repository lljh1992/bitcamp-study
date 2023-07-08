package project.handler;

import util.Prompt;
import vo.NewMember;

public class NewMemberHandler {
  static final int NEWSIZE = 100;
  static NewMember[] nmember = new NewMember[NEWSIZE];

  static int newlength = 0;

  private LoginHandler loginHandler;
  private Prompt prompt;

  public NewMemberHandler(Prompt prompt) {
    this.prompt = prompt;
    this.loginHandler = new LoginHandler(prompt);
  }

  public void execute() {

    printMainMenu();

    while (true) {
      String menuNo = Prompt.inputString("해당 번호를 입력하세요 > ");
      if (menuNo.equals("0")) {
        break;
      } else if (menuNo.equals("menu")) {
        NewMemberHandler.printMainMenu();
      } else if (menuNo.equals("1")) {
        NewMemberHandler.inputNewMember();
      } else if (menuNo.equals("2")) {
        login();
      } else if (menuNo.equals("3")) {
        NewMemberHandler.updateNewMember();
      } else if (menuNo.equals("4")) {
        NewMemberHandler.pirntBoards();
      }
    }

  }

  public static void printMainMenu() {
    System.out.println("1. 회원가입");
    System.out.println("2. 로그인");
    System.out.println("3. 회원정보 변경");
    System.out.println("4. 회원정보 조회");
    System.out.println("0. 종료");
  }


  public static void inputNewMember() {
    if (!newavailable()) {
      System.out.println("더이상 입력할 수 없습니다.");
    }

    NewMember nm = new NewMember();

    nm.setNewid(Prompt.inputString("아이디: "));

    for (int i = 0; i < newlength; i++) {
      if (nm.getNewid().equals(nmember[i].getNewid())) {
        System.out.println("해당 계정을 사용할 수 없습니다!");
        return;
      }
    }

    nm.setNewpassword(Prompt.inputString("비밀번호: "));
    nm.setNewname(Prompt.inputString("이름: "));
    nm.setNewphonenumber(Prompt.inputString("H.P: "));

    nmember[newlength++] = nm;

  }

  public void login() {
    String memberNewId = Prompt.inputString("아이디 : ");
    for (int i = 0; i < newlength; i++) {
      NewMember nm = nmember[i];
      if (nm.getNewid().equals(memberNewId)) {
        String memberNewpw = Prompt.inputString("비밀번호 : ");
        if (nm.getNewpassword().equals(memberNewpw)) {
          loginHandler.execute();
        }
        return;
      }
    }
    System.out.println("등록되지 않은 사용자입니다!");
  }

  public static void pirntBoards() {
    System.out.println("----------------------");
    System.out.println("번호 | 아이디 | 이름 ");
    System.out.println("----------------------");

    for (int i = 0; i < newlength; i++) {
      NewMember nm = nmember[i];
      System.out.printf("%d, %s, %s\n", nm.getNewno(), nm.getNewid(), nm.getNewname());
    }
  }

  public static void updateNewMember() {
    String memberNewId = Prompt.inputString("아이디 : ");
    for (int i = 0; i < newlength; i++) {
      NewMember nm = nmember[i];
      if (nm.getNewid().equals(memberNewId)) {
        String memberNewpw = Prompt.inputString("비밀번호 : ");
        if (nm.getNewpassword().equals(memberNewpw)) {
          System.out.printf("아이디: %s", nm.getNewid());
          nm.setNewid(Prompt.inputString(" > "));
          System.out.printf("비밀번호: %s", nm.getNewpassword());
          nm.setNewpassword(Prompt.inputString(" > "));
          System.out.printf("H.P: %s", nm.getNewphonenumber());
          nm.setNewphonenumber(Prompt.inputString(" > "));
          return;
        }
      }
    }
    System.out.println("등록되지 않은 사용자입니다!");
  }

  public static boolean newavailable() {
    return newlength < NEWSIZE;
  }

}
