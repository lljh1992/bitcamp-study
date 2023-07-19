package project.handler;

import project.util.BreadcrumbPrompt;
import project.util.List;
import project.vo.Member;

public class MemberDeleteListener extends AbstractMemberListener {

  public MemberDeleteListener(List list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    if (!this.list.remove(new Member(prompt.inputInt("번호? ")))) {
      System.out.println("해당 번호의 회원이 없습니다!");
    }
  }

}
