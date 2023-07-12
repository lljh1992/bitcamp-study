package ll.handler;

import java.util.List;
import ll.dao.MemberDao;
import ll.util.ActionListener;
import ll.util.BreadcrumbPrompt;
import ll.vo.Member;

// MemberHandler는 Handler 규칙에 따라 메서드를 구현했다.
// 즉 Handler 인터페이스에 선언된 메서드를 모두 정의했다.
public class MemberListListener implements ActionListener {

  MemberDao memberDao;

  public MemberListListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("----------------------------------------------------------------");
    System.out.println("번호 | 동 | 호수 | 이름 | H.P | 차량번호 | 차량등록현황 | 거주여부");
    System.out.println("----------------------------------------------------------------");

    List<Member> list = memberDao.list();
    for (Member m : list) {
      System.out.printf("%d,     %s, %s, %s, %s, %s, %s, %s\n", m.getNo(), m.getA(), m.getB(),
          m.getName(), m.getPhonenumber(), m.getCarnumber(), m.getVehicleOwnership(),
          m.getType() == 'Y' ? "거주자" : "외부인");

    }
  }
}
