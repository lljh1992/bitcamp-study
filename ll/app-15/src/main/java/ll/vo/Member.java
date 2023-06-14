package ll.vo;

public class Member {

  private static int userID = 1;

  public static final char RESIDENT = 'Y';
  public static final char OUTSIDER = 'N';

  private int no;
  private String a;
  private String b;
  private String name;
  private String phonenumber;
  private String carnumber;
  private String VehicleOwnership;
  private char type;

  public Member() {
    this.no = userID++;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getA() {
    return a;
  }

  public void setA(String a) {
    this.a = a;
  }

  public String getB() {
    return b;
  }

  public void setB(String b) {
    this.b = b;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhonenumber() {
    return phonenumber;
  }

  public void setPhonenumber(String phonenumber) {
    this.phonenumber = phonenumber;
  }

  public String getCarnumber() {
    return carnumber;
  }

  public void setCarnumber(String carnumber) {
    this.carnumber = carnumber;
  }

  public String getVehicleOwnership() {
    return VehicleOwnership;
  }

  public void setVehicleOwnership(String vehicleOwnership) {
    VehicleOwnership = vehicleOwnership;
  }

  public char getType() {
    return type;
  }

  public void setType(char type) {
    this.type = type;
  }


}
