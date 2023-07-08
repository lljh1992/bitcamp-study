package project.handler;

import util.Prompt;
import vo.Member;

public class MemberHandler {

  static final int SIZE = 100;
  static Member[] member = new Member[SIZE];

  static int length = 0;

  static void execute() {

    printMenu();

    while (true) {
      String menuNo = Prompt.inputString("메인> ");
      if (menuNo.equals("0")) {
        LoginHandler.printLoginMenu();
        break;
      } else if (menuNo.equals("menu")) {
        printMenu();
      } else if (menuNo.equals("1")) {
        MemberHandler.inputMember();
      } else if (menuNo.equals("2")) {
        MemberHandler.pirntMembers();
      } else if (menuNo.equals("3")) {
        MemberHandler.viewMember();
      } else if (menuNo.equals("4")) {
        MemberHandler.updateMember();
      } else if (menuNo.equals("5")) {
        MemberHandler.deleteMember();
      } else {
      }
    }
  }

  static void printMenu() {
    System.out.println("1. 차량 등록");
    System.out.println("2. 차량 목록");
    System.out.println("3. 차량 조회");
    System.out.println("4. 정보 변경");
    System.out.println("5. 차량 삭제");
    System.out.println("0. 이전 메뉴");

  }

  public static void inputMember() {
    if (!available()) {
      System.out.println("더이상 입력할 수 없습니다.");
    }

    Member m = new Member();

    m.setA(Prompt.inputString("동: "));
    m.setB(Prompt.inputString("호수: "));
    m.setName(Prompt.inputString("이름: "));
    m.setPhonenumber(Prompt.inputString("H.P: "));
    m.setCarnumber(Prompt.inputString("차량 번호: "));
    m.setVehicleOwnership(Prompt.inputString("차량 보유 현황: "));
    m.setType(inputResident((char)0));

    member[length++] = m;

  }

  public static void pirntMembers() {
    System.out.println("----------------------------------------------------------------");
    System.out.println("번호, 동,   호수,  이름,  H.P,  차량 번호,  차량 보유 현황,  거주 여부");
    System.out.println("----------------------------------------------------------------");

    for (int i = 0; i < length; i++) {
      Member m = member[i];
      System.out.printf("%s,  %s,  %s,  %s,  %s,  %s,  %s,  %c\n",
          m.getNo(), m.getA(), m.getB(), m.getName(), m.getPhonenumber(), m.getCarnumber(),
          m.getVehicleOwnership(), m.getType());
    }
  }

  public static void viewMember() {
    String memberNo = Prompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      Member m = member[i];
      if (m.getNo() == Integer.parseInt(memberNo)) {
        System.out.printf("동: %s\n", m.getA());
        System.out.printf("호수: %s\n", m.getB());
        System.out.printf("이름: %s\n", m.getName());
        System.out.printf("H.P: %s\n", m.getPhonenumber());
        System.out.printf("차량 번호: %s\n", m.getCarnumber());
        System.out.printf("차량 보유 현황: %s\n", m.getVehicleOwnership());
        System.out.printf("거주 여부: %s\n", m.getType());
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  public static String toResidentString(char type){
    return type == 'Y' ? "거주자" : "외부인";
  }

  public static void updateMember() {
    String memberNo = Prompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      Member m = member[i];
      if (m.getNo()  == Integer.parseInt(memberNo)) {
        System.out.printf("동: %s", m.getA());
        m.a = Prompt.inputString(" > ");
        System.out.printf("호수: %s", m.getB());
        m.b = Prompt.inputString(" > ");
        System.out.printf("이름: %s", m.getName());
        m.name = Prompt.inputString(" > ");
        System.out.printf("H.P: %s", m.getPhonenumber());
        m.phonenumber = Prompt.inputString(" > ");
        System.out.printf("차량 번호: %s", m.getCarnumber());
        m.carnumber = Prompt.inputString(" > ");
        System.out.printf("차량 보유 현황: %s", m.getVehicleOwnership());
        m.VehicleOwnership = Prompt.inputString(" > ");
        m.type = inputResident(m.getType());
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  private static char inputResident(char type) {
    String label;
    if (type == 0) {
      label = "거주 여부?\n";
    } else {
      label = String.format("거주 여부(%s)?\n", toResidentString(type));
    }

    while (true) {
      String live = Prompt.inputString(label +
          " 1. 거주자\n" +
          " 2. 외부인\n" +
          " > ");

      switch (live) {
        case "1":
          return  Member.RESIDENT;
        case "2":
          return Member.OUTSIDER;
        default:
          System.out.println("유효한 번호를 입력하세요.");
      }
    }
  }

  public static void deleteMember() {
    int memberNo = Prompt.inputInt("번호? ");
    int deletedIndex = indexOf(memberNo);
    if (deletedIndex == -1) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    for (int i = deletedIndex; i < length -1; i++){
      member[i] = member[i +1];
    }

    member[--length] = null;

  }

  private static int indexOf(int memberNo) {
    for (int i = 0; i < length; i++) {
      Member m = member[i];
      if(m.getNo() == memberNo) {
        return i;
      }
    }
    return -1;
  }

  public static boolean available() {
    return length < SIZE;
  }

}
