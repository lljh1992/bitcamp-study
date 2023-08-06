package project.handler;

import project.dao.MemberDao;
import project.util.BreadcrumbPrompt;
import project.vo.Member;

public class MemberUpdateListener implements MemberActionListener {

  MemberDao memberDao;

  public MemberUpdateListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int memberNo = prompt.inputInt("번호: ");

    Member m = memberDao.findBy(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }


    m.setBuilding(prompt.inputString("동(%s) > ", m.getBuilding()));
    m.setUnit(prompt.inputString("호수(%s) > ", m.getUnit()));
    m.setName(prompt.inputString("이름(%s) > ", m.getName()));
    m.setPhonenumber(prompt.inputString("H.P(%s) > ", m.getPhonenumber()));
    m.setPassword(prompt.inputString("새암호 > "));
    m.setCarnumber(prompt.inputString("차량 번호(%s) > ", m.getCarnumber()));
    m.setVehicleOwnership(prompt.inputString("차량 보유 현황(%s) > ", m.getVehicleOwnership()));
    m.setResidencestatus(MemberActionListener.inputResident(m.getResidencestatus(), prompt));
    memberDao.update(m);



  }
}

