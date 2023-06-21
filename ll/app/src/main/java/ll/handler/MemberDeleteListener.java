package ll.handler;

import ll.util.BreadcrumbPrompt;
import ll.util.List;
import ll.vo.Member;

// MemberHandler는 Handler 규칙에 따라 메서드를 구현했다.
// 즉 Handler 인터페이스에 선언된 메서드를 모두 정의했다.
public class MemberDeleteListener extends AbstractMemberListener {

  public MemberDeleteListener(List list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    if (!this.list.remove(new Member(prompt.inputInt("번호? ")))) {
      System.out.println("해당 번호의 회원이 없습니다.");
    }
  }
}
