package project.handler;

import project.util.BreadcrumbPrompt;
import project.util.List;
import project.vo.Member;

public class MemberAddListener extends AbstractMemberListener {

  public MemberAddListener(List list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Member m = new Member();

    m.setA(prompt.inputString("동: "));
    m.setB(prompt.inputString("호수: "));
    m.setName(prompt.inputString("이름: "));
    m.setPhonenumber(prompt.inputString("H.P: "));
    m.setCarnumber(prompt.inputString("차량 번호: "));
    m.setVehicleOwnership(prompt.inputString("차량 보유 현황: "));
    m.setType(inputResident((char) 0, prompt));

    this.list.add(m);
  }
}
