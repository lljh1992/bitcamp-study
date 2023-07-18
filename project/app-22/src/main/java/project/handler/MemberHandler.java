package project.handler;

import java.time.LocalDateTime;
import util.List;
import util.MenuPrompt;
import vo.Member;

public class MemberHandler implements Handler {

  private List list;
  private MenuPrompt prompt;
  private String title;

  public MemberHandler(MenuPrompt prompt, String title, List list) {
    this.prompt = prompt;
    this.title = title;
    this.list = list;
  }

  @Override
  public void execute() {

    prompt.appendBreadcrumb(this.title, getMenu());
    prompt.printMenu();

    while (true) {
      String menuNo = prompt.inputMenu();
      switch (menuNo) {
        case "0":
          prompt.removeBreadcrumb();
          return;
        case "1":
          this.inputMember();
          break;
        case "2":
          this.printMembers();
          break;
        case "3":
          this.viewMember();
          break;
        case "4":
          this.updateMember();
          break;
        case "5":
          this.deleteMember();
          break;
        case "6":
          this.entryVehicle();
          break;
        case "7":
          this.exitVehicle();
          break;
      }
    }


    // while (true) {
    // String menuNo = prompt.inputString("%s > ", this.title);
    // if (menuNo.equals("0")) {
    // LoginHandler.printLoginMenu();
    // break;
    // } else if (menuNo.equals("menu")) {
    // getMenu();
    // } else if (menuNo.equals("1")) {
    // this.inputMember();
    // } else if (menuNo.equals("2")) {
    // this.printMembers();
    // } else if (menuNo.equals("3")) {
    // this.viewMember();
    // } else if (menuNo.equals("4")) {
    // this.updateMember();
    // } else if (menuNo.equals("5")) {
    // this.deleteMember();
    // } else if (menuNo.equals("6")) {
    // this.entryVehicle();
    // } else if (menuNo.equals("7")) {
    // this.exitVehicle();
    // } else {
    // System.out.println("메뉴 번호가 옳지 않습니다!");
    // }
    // }
  }

  private String getMenu() {
    StringBuilder menu = new StringBuilder();
    menu.append("1. 차량 등록\n");
    menu.append("2. 차량 목록\n");
    menu.append("3. 차량 조회\n");
    menu.append("4. 정보 변경\n");
    menu.append("5. 차량 삭제\n");
    menu.append("6. 차량 입차 기록\n");
    menu.append("7. 차량 출차 기록\n");
    menu.append("0. 이전 메뉴");
    return menu.toString();

  }

  private void inputMember() {
    Member m = new Member();

    m.setA(this.prompt.inputString("동: "));
    m.setB(this.prompt.inputString("호수: "));
    m.setName(this.prompt.inputString("이름: "));
    m.setPhonenumber(this.prompt.inputString("H.P: "));
    m.setCarnumber(this.prompt.inputString("차량 번호: "));
    m.setVehicleOwnership(this.prompt.inputString("차량 보유 현황: "));
    m.setType(inputResident((char) 0));

    this.list.add(m);
  }

  private void printMembers() {
    System.out.println("----------------------------------------------------------------");
    System.out.println("번호, 동,   호수,  이름,  H.P,  차량 번호,  차량 보유 현황,  거주 여부");
    System.out.println("----------------------------------------------------------------");

    for (int i = 0; i < this.list.size(); i++) {
      Member m = (Member) this.list.get(i);
      System.out.printf("%s,  %s,  %s,  %s,  %s,  %s,  %s,  %c\n", m.getNo(), m.getA(), m.getB(),
          m.getName(), m.getPhonenumber(), m.getCarnumber(), m.getVehicleOwnership(), m.getType());
    }
  }

  private void viewMember() {
    int memberNo = this.prompt.inputInt("번호?");

    Member m = this.findBy(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }
    System.out.printf("동: %s\n", m.getA());
    System.out.printf("호수: %s\n", m.getB());
    System.out.printf("이름: %s\n", m.getName());
    System.out.printf("H.P: %s\n", m.getPhonenumber());
    System.out.printf("차량 번호: %s\n", m.getCarnumber());
    System.out.printf("차량 보유 현황: %s\n", m.getVehicleOwnership());
    System.out.printf("거주 여부: %s\n", m.getType());
  }

  private static String toResidentString(char type) {
    return type == 'Y' ? "거주자" : "외부인";
  }

  private void updateMember() {
    int memberNo = this.prompt.inputInt("번호? ");
    Member m = this.findBy(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    m.setA(this.prompt.inputString("동(%s) > ", m.getA()));
    m.setB(this.prompt.inputString("호수(%s) > ", m.getB()));
    m.setName(this.prompt.inputString("이름(%s) > ", m.getName()));
    m.setPhonenumber(this.prompt.inputString("H.P(%s) > ", m.getPhonenumber()));
    m.setCarnumber(this.prompt.inputString("차량 번호(%s) > ", m.getCarnumber()));
    m.setVehicleOwnership(this.prompt.inputString("차량 보유 현황(%s) > ", m.getVehicleOwnership()));
    m.setType(inputResident(m.getType()));

  }

  private char inputResident(char type) {
    String label;
    if (type == 0) {
      label = "거주 여부?\n";
    } else {
      label = String.format("거주 여부(%s)?\n", toResidentString(type));
    }

    while (true) {
      String live = this.prompt.inputString(label + " 1. 거주자\n" + " 2. 외부인\n" + " > ");

      switch (live) {
        case "1":
          return Member.RESIDENT;
        case "2":
          return Member.OUTSIDER;
        default:
          System.out.println("유효한 번호를 입력하세요.");
      }
    }
  }

  private void deleteMember() {
    if (!this.list.remove(new Member(this.prompt.inputInt("번호? ")))) {
      System.out.println("해당 번호의 회원이 없습니다!");
    }
  }

  private void entryVehicle() {
    String recordVehicle = this.prompt.inputString("차량번호: ");
    boolean found = false;

    for (int i = 0; i < this.list.size(); i++) {
      Member m = (Member) this.list.get(i);
      // Object[] arr = this.list.getList();
      // for (Object obj : arr) {
      // Member m = (Member) obj;
      if (m.getCarnumber().equals(recordVehicle)) {
        printEntryTime();
        found = true;
        LocalDateTime entryTime = LocalDateTime.now();
        m.addEntryTime(entryTime);
        m.printEntryTimes();
      }
    }
    if (!found) {
      System.out.println("등록된 차량이 아닙니다.");
    }
  }


  private void exitVehicle() {
    String recordVehicle = this.prompt.inputString("차량번호: ");
    boolean found = false;

    for (int i = 0; i < this.list.size(); i++) {
      Member m = (Member) this.list.get(i);
      // Object[] arr = this.list.getList();
      // for (Object obj : arr) {
      // Member m = (Member) obj;
      if (m.getCarnumber().equals(recordVehicle)) {
        printExitTime();
        found = true;
        LocalDateTime exitTime = LocalDateTime.now();
        m.addExitTime(exitTime);
        m.printExitTimes();
      }
    }
    if (!found) {
      System.out.println("등록된 차량이 아닙니다.");
    }
  }

  private void printEntryTime() {
    System.out.println("------------------------------");
    System.out.println("        차량 입차 기록        ");
    System.out.println("------------------------------");

  }

  private void printExitTime() {
    System.out.println("------------------------------");
    System.out.println("        차량 출차 기록        ");
    System.out.println("------------------------------");

  }

  private Member findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Member m = (Member) this.list.get(i);
      if (m.getNo() == no) {
        return m;
      }
    }
    return null;
  }

}
