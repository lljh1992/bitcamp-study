package project.app;
import java.util.Scanner;

public class App_1 {
  public static void main(String[] args) {
    
   
    Scanner scanner = new Scanner(System.in);

    final int SIZE = 3;
    int userID = 1;
    int length = 0;

    int[] no = new int[SIZE];
    int[] a = new int[SIZE];
    int[] b = new int[SIZE];
    String[] name = new String[SIZE];
    String[] number = new String[SIZE];
    String[] carnumber = new String[SIZE];
    String[] VehicleOwnership = new String[SIZE];
    char[] type = new char[SIZE];    // 05번에서는 boolean 이였는데 switch 문 사용으로 char로 변경

    System.out.println("--------------------------");
    System.out.println("     주차 관리 시스템     ");
    System.out.println("--------------------------");


    for (int i = 0; i < SIZE; i++){
      System.out.print("동? ");
      a[i] = scanner.nextInt();

      System.out.printf("호수? ");
      b[i] = scanner.nextInt();

      System.out.printf("이름? ");
      name[i] = scanner.next();

      System.out.printf("H.P: ");
      number[i] = scanner.next();

      System.out.printf("차량 번호: ");
      carnumber[i] = scanner.next();

      System.out.printf("차량 보유 현황: ");
      VehicleOwnership[i] = scanner.next(); 
       
    loop:while (true) {
      System.out.println("거주 여부: ");
      System.out.println(" 1. 거주자");
      System.out.println(" 2. 외부인");
      System.out.print(" > ");
      String live = scanner.next();
     
      switch (live) {
        case "1":
          type[i] = 'Y';
          break loop;
        case "2":
          type[i] = 'N';
          break loop;
        default:
          System.out.println("유효한 번호를 입력하세요.");
      }
    }

    no[i] = userID++;
    length++;
  
    System.out.print("등록을 계속 하시겠습니까(Y/n)? ");
    scanner.nextLine();
    String response = scanner.nextLine();
    if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
      break;
    }
  }

    System.out.println("-----------------------------------------------------------");
    System.out.println("동, 호수,  이름,  H.P,  차량 번호,  차량 보유 현황,  거주 여부");
    System.out.println("-----------------------------------------------------------");


    for (int i = 0; i < length; i++) {
      System.out.printf("%d,  %d,  %s,  %s,  %s,  %s,  %c\n", a[i], b[i], name[i], number[i], carnumber[i], VehicleOwnership[i], type[i]);
    }
    scanner.close();
  }
}
