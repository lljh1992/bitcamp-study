package ll;

public class App {
  public static void main(String[] args) {
    
    System.out.println("--------------------------");
    System.out.println("주차 관리 시스템");
    System.out.println("--------------------------");

    int a = 108;
    int b = 1704;
    String name = "홍길동";
    String phonenumber = "010-0000-0000";
    String carnumber = "24모 3736";
    String VehicleOwnership = "2대";
    boolean type = true;
    
    System.out.printf("동: %d\n", a);
    System.out.printf("호수: %d\n", b);
    System.out.printf("이름: %s\n", name);
    System.out.printf("H.P: %s\n", phonenumber);
    System.out.printf("차량 번호: %s\n", carnumber);
    System.out.printf("차량 보유 현황: %s\n", VehicleOwnership);
    System.out.printf("거주 여부: %b\n", type);
  }
}
