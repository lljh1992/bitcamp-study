package project.handler;

import util.Prompt;
import vo.NewMember;

public class NewMemberHandler implements Handler {

  private NewMemberHandler list = new NewMemberHandler();
  private Prompt prompt;
  private String title;

  private static LoginHandler loginHandler;

  public NewMemberHandler(Prompt prompt, String title) {
    this.prompt = prompt;
    this.title = title;
    loginHandler = new LoginHandler(prompt, title);
  }

  public NewMemberHandler() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public void execute() {
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
        pirntBoards();
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
    System.out.println("0. 종료");
  }


  private void inputNewMember() {
    NewMember nm = new NewMember();

    nm.setNewpassword(prompt.inputString("비밀번호: "));
    nm.setNewname(prompt.inputString("이름: "));
    nm.setNewphonenumber(prompt.inputString("H.P: "));

    if (!list.add(nm)) {
      System.out.println("더이상 입력할 수 없습니다.");
    }
  }

  private boolean add(NewMember nm) {
    // TODO Auto-generated method stub
    return false;
  }

  public void login() {
    String memberNewId = prompt.inputString("아이디 : ");
    for (int i = 0; i < newlength; i++) {
      NewMember nm = nmember[i];
      if (nm.getNewid().equals(memberNewId)) {
        String memberNewpw = prompt.inputString("비밀번호 : ");
        if (nm.getNewpassword().equals(memberNewpw)) {
          loginHandler.execute();
        } else if(!nm.getNewpassword().equals(memberNewpw)) {
          System.out.println("비밀번호가 틀렸습니다!");
        }
        return;
      }
    }
    System.out.println("등록되지 않은 사용자입니다!");
  }

  private  void pirntBoards() {
    System.out.println("----------------------");
    System.out.println("번호 | 아이디 | 이름 ");
    System.out.println("----------------------");

    NewMember[] arr = this.list.list();


    for (NewMember nm : arr) {
      System.out.printf("%d, %s, %s\n", nm.getNewno(), nm.getNewid(), nm.getNewname());
    }
  }

  private NewMember[] list() {
    // TODO Auto-generated method stub
    return null;
  }

  private  void updateNewMember() {
    String memberNewId = this.prompt.inputString("아이디 : ");
    NewMember nm = this.list.get(memberNewId);


    for (int i = 0; i < newlength; i++) {
      if (nm.getNewid().equals(memberNewId)) {
        String memberNewpw = prompt.inputString("비밀번호 : ");
        if (nm.getNewpassword().equals(memberNewpw)) {
          System.out.printf("아이디: %s", nm.getNewid());
          nm.setNewid(prompt.inputString(" > "));
          System.out.printf("비밀번호: %s", nm.getNewpassword());
          nm.setNewpassword(prompt.inputString(" > "));
          System.out.printf("H.P: %s", nm.getNewphonenumber());
          nm.setNewphonenumber(prompt.inputString(" > "));
          return;
        }
      }
    }
    System.out.println("등록되지 않은 사용자입니다!");
  }

}
