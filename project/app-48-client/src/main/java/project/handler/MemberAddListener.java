package project.handler;

import project.dao.MemberDao;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.vo.Member;

public class MemberAddListener implements ActionListener {

  MemberDao memberDao;

  public MemberAddListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Member m = new Member();

    m.setBuilding(prompt.inputString("동: "));
    m.setUnit(prompt.inputString("호수: "));
    m.setName(prompt.inputString("이름: "));
    m.setPhonenumber(prompt.inputString("H.P: "));
    m.setCarnumber(prompt.inputString("차량 번호: "));
    m.setVehicleOwnership(prompt.inputString("차량 보유 현황: "));
    m.setResidencestatus(MemberActionListener.inputResident((char) 0, prompt));
    memberDao.insert(m);

  }
}
