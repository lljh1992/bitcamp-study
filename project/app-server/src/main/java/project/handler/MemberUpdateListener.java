package project.handler;

import java.io.IOException;
import project.dao.MemberDao;
import project.util.BreadcrumbPrompt;
import project.util.DataSource;
import project.vo.Member;

public class MemberUpdateListener implements MemberActionListener {

  MemberDao memberDao;
  DataSource ds;

  public MemberUpdateListener(MemberDao memberDao, DataSource ds) {
    this.memberDao = memberDao;
    this.ds = ds;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    int memberNo = prompt.inputInt("번호: ");

    Member m = memberDao.findBy(memberNo);
    if (m == null) {
      prompt.println("해당 번호의 회원이 없습니다!");
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

    try {
      memberDao.update(m);
      ds.getConnection().commit();

    } catch (Exception e) {
      try {
        ds.getConnection().rollback();
      } catch (Exception e2) {
      }
      throw new RuntimeException(e);
    }
  }
}

