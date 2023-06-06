package project.app.handler;

import project.util.Prompt;

public class MemberHandler {
    
  static final int SIZE = 3;
  static int userID = 1;
  static int length = 0;

  static int[] no = new int[SIZE];
  static String[] a = new String[SIZE];
  static String[] b = new String[SIZE];
  static String[] name = new String[SIZE];
  static String[] number = new String[SIZE];
  static String[] carnumber = new String[SIZE];
  static String[] VehicleOwnership = new String[SIZE];
  static char[] type = new char[SIZE];    // 05번에서는 boolean 이였는데 switch 문 사용으로 char로 변경

  static final char RESIDENT = 'Y';
  static final char OUTSIDER = 'N';

  public static void inputMember(){
    
    a[length] = Prompt.inputString("동: ");
    b[length] = Prompt.inputString("호수: ");
    name[length] = Prompt.inputString("이름: ");
    number[length] = Prompt.inputString("H.P: ");
    carnumber[length] = Prompt.inputString("차량 번호: ");
    VehicleOwnership[length] = Prompt.inputString("차량 보유 현황: ");
         
  loop:while (true) {
    String live = Prompt.inputString("거주 여부:\n" +
    " 1. 거주자\n" +
    " 2. 외부인\n" +
    " > ");
   
    switch (live) {
      case "1":
        type[length] = RESIDENT;
        break loop;
      case "2":
        type[length] = OUTSIDER;
        break loop;
      default:
        System.out.println("유효한 번호를 입력하세요.");
    }
  }

  no[length] = userID++;
  length++;

  }

  public static void pirntMembers() {
    System.out.println("-----------------------------------------------------------");
    System.out.println("동, 호수,  이름,  H.P,  차량 번호,  차량 보유 현황,  거주 여부");
    System.out.println("-----------------------------------------------------------");

    for (int i = 0; i < length; i++) {
      System.out.printf("%s,  %s,  %s,  %s,  %s,  %s,  %c\n", a[i], b[i], name[i], number[i], carnumber[i], VehicleOwnership[i], type[i]);
    }
  }

  public static boolean available() {
    return length < SIZE;
  }
}
