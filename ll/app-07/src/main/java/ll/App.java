package ll;
import java.util.Scanner;

public class App {
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

    printTitle();
    
    for (int i = 0; i < SIZE; i++){
      inputMember(scanner, i, a, b, name, number, carnumber, VehicleOwnership, type, no, userID++);
      length++;
      if (!promptContinue(scanner)) {
        break;
      }
    

  }
    
    pirntMembers(length, no, a, b, name, number, carnumber, VehicleOwnership, type);

    scanner.close();
  }

  static void printTitle() {
    System.out.println("--------------------------");
    System.out.println("     주차 관리 시스템     ");
    System.out.println("--------------------------");
  }

  static boolean promptContinue(Scanner scanner){
    System.out.print("등록을 계속 하시겠습니까(Y/n)? ");
    scanner.nextLine();
    String response = scanner.nextLine();
    if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
      return false;
    }
    return true;
  }

  

  static void inputMember(Scanner scanner, int i, int[] a, int[] b, String[] name, 
    String[] number, String[] carnumber, String[] VehicleOwnership, char[] type, int[] no, int userID){
    
    
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

  no[i] = userID;

  }



  static void pirntMembers(int length, int[] no, int[] a, int[] b, String[] name, String[] number, String[] carnumber, String[] VehicleOwnership, char[] type) {
    System.out.println("-----------------------------------------------------------");
    System.out.println("동, 호수,  이름,  H.P,  차량 번호,  차량 보유 현황,  거주 여부");
    System.out.println("-----------------------------------------------------------");

    for (int i = 0; i < length; i++) {
      System.out.printf("%d,  %d,  %s,  %s,  %s,  %s,  %c\n", a[i], b[i], name[i], number[i], carnumber[i], VehicleOwnership[i], type[i]);
    }

  }

}
