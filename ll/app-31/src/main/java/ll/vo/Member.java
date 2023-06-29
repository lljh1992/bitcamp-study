package ll.vo;

public class Member {

  public static int userID = 1;

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

  // 같은 기능을 수행하는 생성자가 위에 있다.
  // 다만 파라미터가 다를 뿐이다.
  // => "생성자 오버로딩(overloading)"
  public Member(int no) {
    this.no = no;
  }


  // Object의 equals()는 Member 인스턴스를 비교하는데 적합하지 않다.
  // 왜? Object의 equals()는 단순히 인스턴스 주소가 같은지 비교하기 때문이다.
  // 우리가 원하는 것은 인스턴스 주소가 다르더라도
  // 두 인스턴스 안에 저장된 변수들의 값이 같다면
  // 두 인스턴스는 같은 것으로 처리하는 것이다.
  // 그렇게 하기 위해 수퍼 클래스의 equals()를 재정의 한다.
  // => 이것을 "오버라이딩(overriding)"이라 부른다.
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (this.getClass() != obj.getClass()) {
      return false;
    }

    // 위 조건에서 this가 가리키는 인스턴스의 클래스와
    // 파라미터 obj가 가리키는 인스턴스의 클래스가
    // 같다고 결론이 났기 때문에 다음과 같이
    // obj를 Member 타입으로 형변환한다.
    Member m = (Member) obj;

    if (this.getNo() != m.getNo()) {
      return false;
    }

    // if (this.getName() != null && !this.getName().equals(m.getName())) {
    // return false;
    // }
    //
    // if (this.getEmail() != null && !this.getEmail().equals(m.getEmail())) {
    // return false;
    // }
    //
    // if (this.getPassword() != null && !this.getPassword().equals(m.getPassword())) {
    // return false;
    // }
    //
    // if (this.getGender() != m.getGender()) {
    // return false;
    // }
    return true;
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
