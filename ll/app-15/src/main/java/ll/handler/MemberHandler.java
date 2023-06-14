package ll.handler;

import ll.util.Prompt;
import ll.vo.Member;

public class MemberHandler {

  static final int SIZE = 100;

  private Prompt prompt;

  private Member[] members = new Member[SIZE];


  private int length = 0;

  public MemberHandler(Prompt prompt) {
    this.prompt = prompt;
  }

  public void inputMember() {
    if (!this.available()) {
      System.out.println("더이상 입력할 수 없습니다.");
      return;
    }

    Member m = new Member();

    m.setA(this.prompt.inputString("동: "));
    m.setB(this.prompt.inputString("호수: "));
    m.setName(this.prompt.inputString("이름: "));
    m.setPhonenumber(this.prompt.inputString("H.P: "));
    m.setCarnumber(this.prompt.inputString("차량 번호: "));
    m.setVehicleOwnership(this.prompt.inputString("차량 보유 현황: "));
    m.setType(inputResident((char) 0));

    this.members[this.length++] = m;

  }

  public void pirntMembers() {
    System.out.println("----------------------------------------------------------------");
    System.out.println("번호 | 동 | 호수 | 이름 | H.P | 차량번호 | 차량등록현황 | 거주여부");
    System.out.println("----------------------------------------------------------------");

    for (int i = 0; i < this.length; i++) {
      Member m = this.members[i];
      System.out.printf("%s,     %s, %s, %s, %s, %s, %s, %c\n", m.getNo(), m.getA(), m.getB(),
          m.getName(), m.getPhonenumber(), m.getCarnumber(), m.getVehicleOwnership(), m.getType());
    }
  }

  public void viewMember() {
    String memberNo = this.prompt.inputString("번호? ");
    for (int i = 0; i < this.length; i++) {
      Member m = this.members[i];
      if (m.getNo() == Integer.parseInt(memberNo)) {
        System.out.printf("동: %s\n", m.getA());
        System.out.printf("호수: %s\n", m.getB());
        System.out.printf("이름: %s\n", m.getName());
        System.out.printf("H.P: %s\n", m.getPhonenumber());
        System.out.printf("차량 번호: %s\n", m.getCarnumber());
        System.out.printf("차량 보유 현황: %s\n", m.getVehicleOwnership());
        System.out.printf("거주 여부: %s\n", m.getType());
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  public static String toResidentString(char type) {
    return type == 'Y' ? "거주자" : "외부인";
  }

  public void updateMember() {
    String memberNo = this.prompt.inputString("번호? ");
    for (int i = 0; i < this.length; i++) {
      Member m = this.members[i];
      if (m.getNo() == Integer.parseInt(memberNo)) {
        m.setA(this.prompt.inputString("동: %s", m.getA()));
        m.setB(this.prompt.inputString("호수: %s", m.getB()));
        m.setName(this.prompt.inputString("이름: %s", m.getName()));
        m.setPhonenumber(this.prompt.inputString("H.P: %s", m.getPhonenumber()));
        m.setCarnumber(this.prompt.inputString("차량 번호: %s", m.getCarnumber()));
        m.setVehicleOwnership(this.prompt.inputString("차량 등록 현황: %s", m.getVehicleOwnership()));
        m.setType(inputResident(m.getType()));
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  private char inputResident(char type) {
    String label;
    if (type == 0) {
      label = "거주 여부?\n";
    } else {
      label = String.format("거주 여부(%s)?\n", toResidentString(type));
    }

    while (true) {
      String live = this.prompt.inputString(label + " 1. 거주자\n" + " 2. 외부인\n" + " > ");

      switch (live) {
        case "1":
          return Member.RESIDENT;
        case "2":
          return Member.OUTSIDER;
        default:
          System.out.println("유효한 번호를 입력하세요.");
      }
    }
  }

  public void deleteMember() {
    int memberNo = this.prompt.inputInt("번호? ");

    int deletedIndex = indexOf(memberNo);
    if (deletedIndex == -1) {
      System.out.println("해당 번호의 회원이 없습니다.");
      return;
    }

    for (int i = deletedIndex; i < this.length - 1; i++) {
      this.members[i] = this.members[i + 1];

    }

    this.members[--this.length] = null;

  }

  private int indexOf(int memberNo) {
    for (int i = 0; i < this.length; i++) {
      Member m = this.members[i];
      if (m.getNo() == memberNo) {
        return i;
      }
    }
    return -1;
  }

  public boolean available() {
    return this.length < SIZE;
  }
}
