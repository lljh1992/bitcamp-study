package project.handler;

import java.io.IOException;
import project.dao.MemberDao;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.vo.Member;

public class MemberDetailListener implements ActionListener {

  MemberDao memberDao;

  public MemberDetailListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    int memberNo = prompt.inputInt("번호: ");

    Member m = memberDao.findBy(memberNo);
    if (m == null) {
      prompt.println("해당 번호의 회원이 없습니다.");
      return;
    }
    prompt.printf("동-호수: %s\n", m.getBuilding());
    prompt.printf("이름: %s\n", m.getName());
    prompt.printf("H.P: %s\n", m.getPhonenumber());
    prompt.printf("차량 번호: %s\n", m.getCarnumber());
    prompt.printf("차량 보유 현황: %s\n", m.getVehicleOwnership());
    prompt.printf("거주 여부: %s\n", m.getResidencestatus() == 'Y' ? "거주자" : "외부인");
    prompt.printf("가입일: %s\n", m.getCreatedDate());
  }

}
