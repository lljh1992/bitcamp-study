package project.controller;

import java.util.List;
import project.util.BreadcrumbPrompt;
import project.vo.Member;

public class MemberDeleteListener extends AbstractMemberListener {

  public MemberDeleteListener(List<Member> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    if (!this.list.remove(new Member(prompt.inputInt("번호? ")))) {
      System.out.println("해당 번호의 회원이 없습니다!");
    }
  }

}
