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
    String memberNo = prompt.inputString("동-호수: ");

    Member m = memberDao.findBy(memberNo);
    if (m == null) {
      prompt.println("해당 번호의 회원이 없습니다.");
      return;
    }
    prompt.printf("이름: %s\n", m.getName());
    prompt.printf("H.P: %s\n", m.getPhonenumber());
    prompt.printf("차량 번호: %s\n", m.getCarnumber());
    prompt.printf("등록일: %s\n", m.getCreatedDate());
    prompt.printf("동-호수: %s\n", m.getBuilding());
  }

}
