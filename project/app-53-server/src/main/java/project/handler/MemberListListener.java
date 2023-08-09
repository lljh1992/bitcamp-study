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
    prompt.println("----------------------------------------------------------------");
    prompt.println("동-호수,  이름,  H.P,  차량 번호");
    prompt.println("----------------------------------------------------------------");

    List<Member> list = memberDao.findAll();
    for (Member member : list) {
      prompt.printf("%s,  %s,  %s,  %s\n", member.getBuilding(), member.getName(),
          member.getPhonenumber(), member.getCarnumber());
    }
  }

}
