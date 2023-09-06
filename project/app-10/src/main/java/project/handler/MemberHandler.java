package project.controller;

import util.Prompt;

public class MemberHandler {
  static final int SIZE = 100;
  static int userID = 1;
  static int length = 0;

  static int[] no = new int[SIZE];
  static String[] a = new String[SIZE];
  static String[] b = new String[SIZE];
  static String[] name = new String[SIZE];
  static String[] phonenumber = new String[SIZE];
  static String[] carnumber = new String[SIZE];
  static String[] VehicleOwnership = new String[SIZE];
  static char[] type = new char[SIZE];

  static final char RESIDENT = 'Y';
  static final char OUTSIDER = 'N';

  static void execute() {

    printMenu();

    while (true) {
      String menuNo = Prompt.inputString("메인> ");
      if (menuNo.equals("6")) {
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
        System.out.println(menuNo);
      }
    }

    Prompt.close();
  }

  static void printMenu() {
    System.out.println("1. 차량 등록");
    System.out.println("2. 차량 목록");
    System.out.println("3. 차량 조회");
    System.out.println("4. 정보 변경");
    System.out.println("5. 차량 삭제");
    System.out.println("6. 종료");

  }

  public static void inputMember() {
    if (!available()) {
      System.out.println("더이상 입력할 수 없습니다.");
    }

    a[length] = Prompt.inputString("동: ");
    b[length] = Prompt.inputString("호수: ");
    name[length] = Prompt.inputString("이름: ");
    phonenumber[length] = Prompt.inputString("H.P: ");
    carnumber[length] = Prompt.inputString("차량 번호: ");
    VehicleOwnership[length] = Prompt.inputString("차량 보유 현황: ");
    type[length] = inputResident((char)0);


    no[length] = userID++;
    length++;

  }

  public static void pirntMembers() {
    System.out.println("----------------------------------------------------------------");
    System.out.println("번호, 동,   호수,  이름,  H.P,  차량 번호,  차량 보유 현황,  거주 여부");
    System.out.println("----------------------------------------------------------------");

    for (int i = 0; i < length; i++) {
      System.out.printf("%s,  %s,  %s,  %s,  %s,  %s,  %s,  %c\n",
          no[i], a[i], b[i], name[i], phonenumber[i], carnumber[i],
          VehicleOwnership[i], type[i]);
    }
  }

  public static void viewMember() {
    String memberNo = Prompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      if (no[i] == Integer.parseInt(memberNo)) {
        System.out.printf("동: %s\n", a[i]);
        System.out.printf("호수: %s\n", b[i]);
        System.out.printf("이름: %s\n", name[i]);
        System.out.printf("H.P: %s\n", phonenumber[i]);
        System.out.printf("차량 번호: %s\n", carnumber[i]);
        System.out.printf("차량 보유 현황: %s\n", VehicleOwnership[i]);
        System.out.printf("거주 여부: %s\n", type[i]);
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
      if (no[i] == Integer.parseInt(memberNo)) {
        System.out.printf("동: %s", a[i]);
        a[i] = Prompt.inputString(" > ");
        System.out.printf("호수: %s", b[i]);
        b[i] = Prompt.inputString(" > ");
        System.out.printf("이름: %s", name[i]);
        name[i] = Prompt.inputString(" > ");
        System.out.printf("H.P: %s", phonenumber[i]);
        phonenumber[i] = Prompt.inputString(" > ");
        System.out.printf("차량 번호: %s", carnumber[i]);
        carnumber[i] = Prompt.inputString(" > ");
        System.out.printf("차량 보유 현황: %s", VehicleOwnership[i]);
        VehicleOwnership[i] = Prompt.inputString(" > ");
        type[i] = inputResident(type[i]);
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
          return  RESIDENT;
        case "2":
          return OUTSIDER;
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
      no[i] = no[i +1];
      a[i] = a[i +1];
      b[i] = b[i +1];
      name[i] = name[i +1];
      phonenumber[i] = phonenumber[i +1];
      carnumber[i] = carnumber[i +1];
      VehicleOwnership[i] = VehicleOwnership[i +1];
      type[i] = type[i +1];
    }

    no[length - 1] = 0;
    a[length - 1] = null;
    b[length - 1] = null;
    name[length - 1] = null;
    phonenumber[length - 1] = null;
    carnumber[length - 1] = null;
    VehicleOwnership[length - 1] = null;
    type[length - 1] = (char) 0;

    length--;
  }

  private static int indexOf(int memberNo) {
    for (int i = 0; i < length; i++) {
      if(no[i] == memberNo) {
        return i;
      }
    }
    return -1;
  }

  public static boolean available() {
    return length < SIZE;
  }

}
