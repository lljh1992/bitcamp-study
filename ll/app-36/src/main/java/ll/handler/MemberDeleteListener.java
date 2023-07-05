package ll.handler;

import ll.dao.MemberDao;
import ll.util.ActionListener;
import ll.util.BreadcrumbPrompt;

// MemberHandler는 Handler 규칙에 따라 메서드를 구현했다.
// 즉 Handler 인터페이스에 선언된 메서드를 모두 정의했다.
public class MemberDeleteListener implements ActionListener {

  MemberDao memberDao;

  public MemberDeleteListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    if (memberDao.delete(prompt.inputInt("번호? ")) == 0) {
      System.out.println("해당 번호의 회원이 없습니다.");
    }
  }
}
