package project.controller;

import java.io.IOException;
import project.dao.MemberDao;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.util.DataSource;

public class MemberDeleteListener implements ActionListener {

  MemberDao memberDao;
  DataSource ds;

  public MemberDeleteListener(MemberDao memberDao, DataSource ds) {
    this.memberDao = memberDao;
    this.ds = ds;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    try {
      if (memberDao.delete(prompt.inputInt("번호: ")) == 0) {
        prompt.println("해당 번호의 회원이 없습니다.");
      }
      prompt.println("삭제되었습니다.");
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
