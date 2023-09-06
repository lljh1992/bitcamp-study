package project.controller;

import project.util.BreadcrumbPrompt;
import project.util.List;
import project.vo.Member;

public class MemberUpdateListener extends AbstractMemberListener {

  public MemberUpdateListener(List list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int memberNo = prompt.inputInt("번호? ");
    Member m = this.findBy(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    m.setA(prompt.inputString("동(%s) > ", m.getA()));
    m.setB(prompt.inputString("호수(%s) > ", m.getB()));
    m.setName(prompt.inputString("이름(%s) > ", m.getName()));
    m.setPhonenumber(prompt.inputString("H.P(%s) > ", m.getPhonenumber()));
    m.setCarnumber(prompt.inputString("차량 번호(%s) > ", m.getCarnumber()));
    m.setVehicleOwnership(prompt.inputString("차량 보유 현황(%s) > ", m.getVehicleOwnership()));
    m.setType(inputResident(m.getType(), prompt));

  }

}
