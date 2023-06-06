package project.app;
import java.util.Scanner;

public class App_1 {
  public static void main(String[] args) {
       
    Scanner scanner = new Scanner(System.in);

    final int SIZE = 3;

    int[] a = new int[SIZE];
    int[] b = new int[SIZE];
    String[] name = new String[SIZE];
    String[] number = new String[SIZE];
    String[] carnumber = new String[SIZE];
    String[] VehicleOwnership = new String[SIZE];
    boolean[] type = new boolean[SIZE];

    System.out.println("--------------------------");
    System.out.println("주차 관리 시스템");
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

      System.out.print("거주 여부: ");   
      type[i] = scanner.nextBoolean();
    }


    System.out.println("--------------------------");

    for (int i = 0; i < SIZE; i++) {
      System.out.printf("동: %d\n", a[i]);
      System.out.printf("호수: %d\n", b[i]);
      System.out.printf("이름: %s\n", name[i]);
      System.out.printf("H.P: %s\n", number[i]);
      System.out.printf("차량 번호: %s\n", carnumber[i]);
      System.out.printf("차량 보유 현황: %s\n", VehicleOwnership[i]);
      System.out.printf("거주 여부: %b\n", type[i]);
    }
    scanner.close();
  }
}
