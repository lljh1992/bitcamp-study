package project.handler;

import project.util.BreadcrumbPrompt;
import project.util.List;
import project.vo.Member;

public class MemberListListener extends AbstractMemberListener {

  public MemberListListener(List<Member> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("----------------------------------------------------------------");
    System.out.println("번호, 동,   호수,  이름,  H.P,  차량 번호,  차량 보유 현황,  거주 여부");
    System.out.println("----------------------------------------------------------------");

    for (int i = 0; i < this.list.size(); i++) {
      Member m = this.list.get(i);
      System.out.printf("%s,  %s,  %s,  %s,  %s,  %s,  %s,  %c\n", m.getNo(), m.getA(), m.getB(),
          m.getName(), m.getPhonenumber(), m.getCarnumber(), m.getVehicleOwnership(), m.getType());
    }
  }

}
