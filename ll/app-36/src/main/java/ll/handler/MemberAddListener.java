package ll.handler;

import ll.dao.MemberDao;
import ll.util.BreadcrumbPrompt;
import ll.vo.Member;

// MemberHandler는 Handler 규칙에 따라 메서드를 구현했다.
// 즉 Handler 인터페이스에 선언된 메서드를 모두 정의했다.
public class MemberAddListener implements MemberActionListener {

  MemberDao memberDao;

  public MemberAddListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Member m = new Member();
    m.setA(prompt.inputString("동: "));
    m.setB(prompt.inputString("호수: "));
    m.setName(prompt.inputString("이름: "));
    m.setPhonenumber(prompt.inputString("H.P: "));
    m.setCarnumber(prompt.inputString("차량 번호: "));
    m.setVehicleOwnership(prompt.inputString("차량 보유 현황: "));
    m.setType(MemberActionListener.inputResident((char) 0, prompt));

    memberDao.insert(m);

  }
}
