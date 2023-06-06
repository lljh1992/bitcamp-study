package project.app;
import java.util.Scanner;

public class App_1 {
  public static void main(String[] args) {
    
    System.out.println("--------------------------");
    System.out.println("주차 관리 시스템");
    System.out.println("--------------------------");

    Scanner scanner = new Scanner(System.in);

    System.out.print("동? ");
    int a = scanner.nextInt();

    System.out.printf("호수? ");
    int b = scanner.nextInt();

    System.out.printf("이름? ");
    String name = scanner.next();

    System.out.printf("H.P: ");
    String number = scanner.next();

    System.out.printf("차량 번호: ");
    String carnumber = scanner.next();

    System.out.printf("차량 보유 현황: ");
    String VehicleOwnership = scanner.next();

    System.out.print("거주 여부: ");   
    boolean type = scanner.nextBoolean();
    
    System.out.println("--------------------------");

    System.out.printf("동: %d\n", a);
    System.out.printf("호수: %d\n", b);
    System.out.printf("이름: %s\n", name);
    System.out.printf("H.P: %s\n", number);
    System.out.printf("차량 번호: %s\n", carnumber);
    System.out.printf("차량 보유 현황: %s\n", VehicleOwnership);
    System.out.printf("거주 여부: %b\n", type);

  }
}
