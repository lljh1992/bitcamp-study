package project.handler;

import java.util.List;
import project.dao.MemberDao;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.vo.Member;

public class MemberListListener implements ActionListener {

  MemberDao memberDao;

  public MemberListListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("----------------------------------------------------------------");
    System.out.println("번호, 동,   호수,  이름,  H.P,  차량 번호,  차량 보유 현황,  거주 여부");
    System.out.println("----------------------------------------------------------------");

    List<Member> list = memberDao.list();
    for (Member member : list) {
      System.out.printf("%s,  %s,  %s,  %s,  %s,  %s,  %s,  %c\n", member.getNo(),
          member.getBuilding(), member.getUnit(), member.getName(), member.getPhonenumber(),
          member.getCarnumber(), member.getVehicleOwnership(), member.getResidencestatus());
    }
  }

}
