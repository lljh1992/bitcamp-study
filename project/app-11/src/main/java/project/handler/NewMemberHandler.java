package project.controller;

import util.Prompt;

public class NewMemberHandler {
  static final int NEWSIZE = 100;
  static int newuserID = 1;
  static int newlength = 0;

  static int[] newno = new int[NEWSIZE];
  static String[] newid = new String[NEWSIZE];
  static String[] newpassword = new String[NEWSIZE];
  static String[] newname = new String[NEWSIZE];
  static String[] newphonenumber = new String[NEWSIZE];


  public void execute() {

    printMainMenu();

  }

  public static void printMainMenu() {
    System.out.println("1. 회원가입");
    System.out.println("2. 로그인");
    System.out.println("0. 종료");
  }


  public static void inputNewMember() {
    if (!newavailable()) {
      System.out.println("더이상 입력할 수 없습니다.");
    }

    newid[newlength] = Prompt.inputString("아이디: ");
    newpassword[newlength] = Prompt.inputString("비밀번호: ");
    newname[newlength] = Prompt.inputString("이름: ");
    newphonenumber[newlength] = Prompt.inputString("H.P: ");

    newno[newlength] = newuserID++;
    newlength++;

  }

  public static void login() {
    String memberNewId = Prompt.inputString("아이디 : ");
    for (int i = 0; i < newlength; i++) {
      if (newid[i].equals(memberNewId)) {
        String memberNewpw = Prompt.inputString("비밀번호 : ");
        if (newpassword[i].equals(memberNewpw)) {
          MemberHandler.execute();
        }
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  public static boolean newavailable() {
    return newlength < NEWSIZE;
  }

}
