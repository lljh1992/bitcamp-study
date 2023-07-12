package ll.handler;

import ll.dao.MemberDao;
import ll.util.BreadcrumbPrompt;
import ll.vo.Member;

// MemberHandler는 Handler 규칙에 따라 메서드를 구현했다.
// 즉 Handler 인터페이스에 선언된 메서드를 모두 정의했다.
public class MemberUpdateListener implements MemberActionListener {

  MemberDao memberDao;

  public MemberUpdateListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int memberNo = prompt.inputInt("번호? ");

    Member m = memberDao.findBy(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }
    m.setA(prompt.inputString("동:(%s)? ", m.getA()));
    m.setB(prompt.inputString("호수:(%s)? ", m.getB()));
    m.setName(prompt.inputString("이름:(%s)? ", m.getName()));
    m.setPhonenumber(prompt.inputString("H.P:(%s)? ", m.getPhonenumber()));
    m.setCarnumber(prompt.inputString("차량 번호:(%s)? ", m.getCarnumber()));
    m.setVehicleOwnership(prompt.inputString("차량 등록 현황:(%s)? ", m.getVehicleOwnership()));
    m.setType(MemberActionListener.inputResident(m.getType(), prompt));

    memberDao.update(m);
  }
}

