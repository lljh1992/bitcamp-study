package project.handler;

import util.ArrayList;
import util.Prompt;
import vo.NewMember;

public class NewMemberHandler implements Handler {

  private ArrayList list = new ArrayList();
  private Prompt prompt;
  private String title;

  private static LoginHandler loginHandler;

  public NewMemberHandler(Prompt prompt, String title) {
    this.prompt = prompt;
    this.title = title;
    loginHandler = new LoginHandler(prompt, title);
  }

  @Override
  public void execute() {

    // NewMemberHandler newmemberHandler = new NewMemberHandler(prompt, title);

    printMainMenu();

    while (true) {
      String menuNo = prompt.inputString("%s > ", this.title);
      if (menuNo.equals("0")) {
        return;
      } else if (menuNo.equals("menu")) {
        printMainMenu();
      } else if (menuNo.equals("1")) {
        this.inputNewMember();
      } else if (menuNo.equals("2")) {
        login();
      } else if (menuNo.equals("3")) {
        updateNewMember();
      } else if (menuNo.equals("4")) {
        printBoards();
      } else if (menuNo.equals("5")) {
        deleteNewMember();
      } else {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }
  }

  public static void printMainMenu() {
    System.out.println("1. 회원가입");
    System.out.println("2. 로그인");
    System.out.println("3. 회원정보 변경");
    System.out.println("4. 회원정보 조회");
    System.out.println("5. 회원탈퇴");
    System.out.println("0. 종료");
  }

  private void inputNewMember() {
    NewMember nm = new NewMember();

    String newMemberid = prompt.inputString("아이디: ");

    if (isExistingMember(newMemberid)) {
      System.out.println("해당 계정을 사용할 수 없습니다!");
      return;
    }

    nm.setNewid(newMemberid);
    nm.setNewpassword(prompt.inputString("비밀번호: "));
    nm.setNewname(prompt.inputString("이름: "));
    nm.setNewphonenumber(prompt.inputString("H.P: "));

    if (!this.list.add(nm)) {
      System.out.println("더 이상 입력할 수 없습니다.");
    }

  }

  public void login() {
    String memberNewId = prompt.inputString("아이디: ");
    String memberNewPw = prompt.inputString("비밀번호: ");

    Object[] arr = list.list();
    for (Object obj : arr) {
      NewMember nm = (NewMember) obj;
      if (nm != null && nm.getNewid().equals(memberNewId)) {
        if (nm.getNewpassword().equals(memberNewPw)) {
          loginHandler.execute();
          return;
        } else {
          System.out.println("비밀번호가 틀렸습니다!");
          return;
        }
      }
    }
    System.out.println("등록되지 않은 사용자입니다!");
  }

  private void printBoards() {
    System.out.println("----------------------");
    System.out.println("번호 | 아이디 | 이름 ");
    System.out.println("----------------------");

    Object[] arr = this.list.list();
    for (Object obj : arr) {
      NewMember nm = (NewMember) obj;
      System.out.printf("%d, %s, %s\n", nm.getNewno(), nm.getNewid(), nm.getNewname());
    }
  }

  private void updateNewMember() {
    String memberNewId = this.prompt.inputString("아이디 : ");

    Object[] arr = this.list.list();
    for (Object obj : arr) {
      NewMember nm = (NewMember) obj;
      if (nm.getNewid().equals(memberNewId)) {
        String memberNewpw = prompt.inputString("비밀번호 : ");
        if (nm.getNewpassword().equals(memberNewpw)) {
          System.out.printf("아이디: %s", nm.getNewid());

          String newId = prompt.inputString(" > ");
          if (isExistingMember(newId)) {
            System.out.println("사용할 수 없는 아이디입니다!");
            return;
          }
          nm.setNewid(newId);

          System.out.printf("비밀번호: %s", nm.getNewpassword());
          nm.setNewpassword(prompt.inputString(" > "));
          System.out.printf("H.P: %s", nm.getNewphonenumber());
          nm.setNewphonenumber(prompt.inputString(" > "));
          return;
        } else {
          System.out.println("비밀번호가 틀렸습니다!");
          return;
        }
      }
    }
    System.out.println("등록되지 않은 사용자입니다!");
  }

  private boolean isExistingMember(String newmemberid) {
    Object[] arr = this.list.list();
    for (Object obj : arr) {
      NewMember nm = (NewMember) obj;
      if (nm != null && nm.getNewid().equals(newmemberid)) {
        return true;
      }
    }
    return false;
  }

  private void deleteNewMember() {
    String memberNewId = this.prompt.inputString("아이디: ");

    Object[] arr = this.list.list();
    for (Object obj : arr) {
      NewMember nm = (NewMember) obj;
      if (nm.getNewid().equals(memberNewId)) {
        String memberNewpw = prompt.inputString("비밀번호: ");
        if (nm.getNewpassword().equals(memberNewpw)) {
          String response = prompt.inputString("회원탈퇴를 진행하시겠습니까? (Y/n) ");
          if (response.equals("") || response.equalsIgnoreCase("Y")) {
            if (this.list.deleteLogin(memberNewId)) {
              System.out.println("회원탈퇴가 완료되었습니다.");
            } else {
              System.out.println("회원탈퇴에 실패하였습니다.");
            }
          } else {
            System.out.println("회원탈퇴가 취소되었습니다.");
          }
          return;
        } else {
          System.out.println("비밀번호가 틀렸습니다!");
          return;
        }
      }
    }
    System.out.println("등록되지 않은 사용자입니다!");
  }

}
