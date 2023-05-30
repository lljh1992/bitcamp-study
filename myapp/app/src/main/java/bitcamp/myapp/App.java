package bitcamp.myapp;

// 코드 본문에서 사용할 클래스가 어떤 패키지의 클래스인지 지정한다.

import java.util.Scanner;
public class App {
      
    public static void main(String[] args) {
      System.out.println("나의 목록 관리 시스템");
      System.out.println("----------------------------");


      // 키보드 스캐너 준비
      
      Scanner scanner = new Scanner(System.in);

      System.out.print("번호? ");
      int no = scanner.nextInt();
      
      System.out.print("이름? ");
      String name = scanner.next();

      System.out.print("나이? ");
      int age = scanner.nextInt();

      System.out.print("재직중? ");
      boolean working = scanner.nextBoolean();

      System.out.print("성별(남자:M, 여자:W)? ");
      String str = scanner.next();
      char gender = str.charAt(0);

      System.out.print("시력(왼쪽, 오른쪽) ");
      float leftEye = scanner.nextFloat();
      float rightEye = scanner.nextFloat();

      System.out.print("번호? ");
      int no1 = scanner.nextInt();
      
      System.out.print("이름? ");
      String name1 = scanner.next();

      System.out.print("나이? ");
      int age1 = scanner.nextInt();

      System.out.print("재직중? ");
      boolean working1 = scanner.nextBoolean();

      System.out.print("성별(남자:M, 여자:W)? ");
      str = scanner.next();
      char gender1 = str.charAt(0);

      System.out.print("시력(왼쪽, 오른쪽) ");
      float leftEye1 = scanner.nextFloat();
      float rightEye1 = scanner.nextFloat();

      System.out.print("번호? ");
      int no2 = scanner.nextInt();
      
      System.out.print("이름? ");
      String name2 = scanner.next();

      System.out.print("나이? ");
      int age2 = scanner.nextInt();

      System.out.print("재직중? ");
      boolean working2 = scanner.nextBoolean();

      System.out.print("성별(남자:M, 여자:W)? ");
      str = scanner.next();
      char gender2 = str.charAt(0);

      System.out.print("시력(왼쪽, 오른쪽) ");
      float leftEye2 = scanner.nextFloat();
      float rightEye2 = scanner.nextFloat();

      System.out.println("-----------------------------");
   
      System.out.printf("번호 : %d\n", no);
      System.out.printf("이름 : %s\n", name);
      System.out.printf("나이 : %d\n", age);
      System.out.printf("재직자 : %b\n", working);
      System.out.printf("성벌(남자(M), 여자(W)) : %c\n", gender);
      System.out.printf("좌우시력 : %f,%f\n", leftEye, rightEye);

      System.out.printf("번호 : %d\n", no1);
      System.out.printf("이름 : %s\n", name1);
      System.out.printf("나이 : %d\n", age1);
      System.out.printf("재직자 : %b\n", working1);
      System.out.printf("성벌(남자(M), 여자(W)) : %c\n", gender1);
      System.out.printf("좌우시력 : %f,%f\n", leftEye1, rightEye1);

      System.out.printf("번호 : %d\n", no2);
      System.out.printf("이름 : %s\n", name2);
      System.out.printf("나이 : %d\n", age2);
      System.out.printf("재직자 : %b\n", working2);
      System.out.printf("성벌(남자(M), 여자(W)) : %c\n", gender2);
      System.out.printf("좌우시력 : %f,%f\n", leftEye2, rightEye2);

            scanner.close();

    }

  }