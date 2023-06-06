package bitcamp.myapp;

<<<<<<< HEAD
// 코드 본문에서 사용할 클래스가 어떤 패키지인지 지정한다.
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    System.out.println("나의 목록 관리 시스템");
    System.out.println("-----------------------------------");

    // 키보드 스캐너 준비
    Scanner scanner = new Scanner(System.in);

    int[] no = new int[3];
    String[] name = new String[3];
    int[] age = new int[3];
    boolean[] working = new boolean[3];
    char[] gender = new char[3];
    float[] leftEye = new float[3];
    float[] rightEye = new float[3];

    for (int count = 0; count < 3; count++) { // for(1.처음, )

      System.out.print("번호? : ");
      no[count] = scanner.nextInt();

      System.out.print("이름? : ");
      name[count] = scanner.next();

      System.out.print("나이? : ");
      age[count] = scanner.nextInt();

      System.out.print("재직 여부? : ");
      working[count] = scanner.nextBoolean();

      System.out.print("성별? : ");
      String str = scanner.next();
      gender[count] = str.charAt(0);

      System.out.print("좌우시력? : ");
      leftEye[count] = scanner.nextFloat();
      rightEye[count] = scanner.nextFloat();

      // count++; // = count += +1;// = count = count +1;
=======
import bitcamp.myapp.handler.MemberHandler;
import bitcamp.util.Prompt;

// 코드 본문에서 사용할 클래스가 어떤 패키지의 클래스인지 지정한다.

public class App {
    public static void main(String[] args) {

    printTitle();
>>>>>>> 67ae9ed06ef9111ff8779ee624a2601bd4f87f93

    while (MemberHandler.available()) {
      MemberHandler.inputMember();
      if (!promptContinue()) {
        break;
      }
    }

<<<<<<< HEAD
    System.out.println("---------------------------------");

    for (int count = 0; count < 3; count++) {
      System.out.printf("번호 : %d\n", no[count]);
      System.out.printf("이름: %s\n", name[count]); // s : 문자열
      System.out.printf("나이: %d\n", age[count]);
      System.out.printf("재직자: %b\n", working[count]); // 출력후 줄바꿈, \n : escape character
      System.out.printf("성벌(남자(M), 여자(W)): %c\n", gender[count]); // c: 문자(문자열이랑 다름)
      System.out.printf("좌우시력: %f,%f\n", leftEye[count], rightEye[count]);
    }
  }
=======
    MemberHandler.printMembers();

    Prompt.close();
  }

  static void printTitle() {
    System.out.println("나의 목록 관리 시스템");
    System.out.println("----------------------------------");
  }
 
  static boolean promptContinue() {
    System.out.print("계속 하시겠습니까?(Y/n) ");
    String response = Prompt.scanner.nextLine();
    if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
      return false;
    }
    return true;
  }
   
>>>>>>> 67ae9ed06ef9111ff8779ee624a2601bd4f87f93
}