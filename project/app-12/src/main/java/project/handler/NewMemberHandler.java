package project.handler;

import util.Prompt;
import vo.NewMember;

public class NewMemberHandler {
  static final int NEWSIZE = 100;
  static NewMember[] nmember = new NewMember[NEWSIZE];

  static int newlength = 0;

  public void execute() {

    printMainMenu();

  }

  public static void printMainMenu() {
    System.out.println("1. 회원가입");
    System.out.println("2. 로그인");
    System.out.println("3. 회원정보 변경");
    System.out.println("0. 종료");
  }


  public static void inputNewMember() {
    if (!newavailable()) {
      System.out.println("더이상 입력할 수 없습니다.");
    }

    NewMember nm = new NewMember();

    nm.setNewid(Prompt.inputString("아이디: "));
    nm.setNewpassword(Prompt.inputString("비밀번호: "));
    nm.setNewname(Prompt.inputString("이름: "));
    nm.setNewphonenumber(Prompt.inputString("H.P: "));

    nmember[newlength++] = nm;

  }

  public static void login() {
    String memberNewId = Prompt.inputString("아이디 : ");
    for (int i = 0; i < newlength; i++) {
      NewMember nm = nmember[i];
      if (nm.getNewid().equals(memberNewId)) {
        String memberNewpw = Prompt.inputString("비밀번호 : ");
        if (nm.getNewpassword().equals(memberNewpw)) {
          MemberHandler.execute();
        }
        return;
      }
    }
    System.out.println("등록되지 않은 사용자입니다!");
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
