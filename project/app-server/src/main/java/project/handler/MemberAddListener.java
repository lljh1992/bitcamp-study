package project.handler;

import java.io.IOException;
import project.dao.MemberDao;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.util.DataSource;
import project.vo.Member;

public class MemberAddListener implements ActionListener {

  MemberDao memberDao;
  DataSource ds;

  public MemberAddListener(MemberDao memberDao, DataSource ds) {
    this.memberDao = memberDao;
    this.ds = ds;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Member m = new Member();

    m.setBuilding(prompt.inputString("동: "));
    m.setUnit(prompt.inputString("호수: "));
    m.setName(prompt.inputString("이름: "));
    m.setPhonenumber(prompt.inputString("H.P: "));
    m.setPassword(prompt.inputString("암호: "));
    m.setCarnumber(prompt.inputString("차량 번호: "));
    m.setVehicleOwnership(prompt.inputString("차량 보유 현황: "));
    m.setResidencestatus(MemberActionListener.inputResident((char) 0, prompt));

    try {
      memberDao.insert(m);
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
