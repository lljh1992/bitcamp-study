package project.handler;

import project.util.BreadcrumbPrompt;
import project.util.List;
import project.vo.Member;

public class MemberDetailListener extends AbstractMemberListener {


  public MemberDetailListener(List list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int memberNo = prompt.inputInt("번호?");

    Member m = this.findBy(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }
    System.out.printf("동: %s\n", m.getA());
    System.out.printf("호수: %s\n", m.getB());
    System.out.printf("이름: %s\n", m.getName());
    System.out.printf("H.P: %s\n", m.getPhonenumber());
    System.out.printf("차량 번호: %s\n", m.getCarnumber());
    System.out.printf("차량 보유 현황: %s\n", m.getVehicleOwnership());
    System.out.printf("거주 여부: %s\n", m.getType());
  }

}
