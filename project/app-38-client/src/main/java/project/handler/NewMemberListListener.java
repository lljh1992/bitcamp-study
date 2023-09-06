package project.controller;

import java.util.List;
import project.dao.NewMemberDao;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.vo.NewMember;

public class NewMemberListListener implements ActionListener {

  NewMemberDao newmemberDao;

  public NewMemberListListener(NewMemberDao newmemberDao) {
    this.newmemberDao = newmemberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("----------------------");
    System.out.println("번호 | 아이디 | 이름 ");
    System.out.println("----------------------");

    List<NewMember> list = newmemberDao.list();

    for (NewMember newmember : list) {
      System.out.printf("%d, %s, %s\n", newmember.getNewno(), newmember.getNewid(),
          newmember.getNewname());
    }
  }

}
