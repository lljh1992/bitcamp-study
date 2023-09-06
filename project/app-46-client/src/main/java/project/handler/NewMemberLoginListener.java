package project.controller;

import project.ClientApp;
import project.dao.NewMemberDao;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.vo.NewMember;

public class NewMemberLoginListener implements ActionListener {

  NewMemberDao newmemberDao;

  public NewMemberLoginListener(NewMemberDao newmemberDao) {
    this.newmemberDao = newmemberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    String memberNewId = prompt.inputString("아이디: ");
    String memberNewPw = prompt.inputString("비밀번호: ");

    if (newmemberDao.isExistingMember(memberNewId)) {
      NewMember newmember = newmemberDao.findByNewId(memberNewId);
      if (newmember != null && newmember.getNewpassword().equals(memberNewPw)) {
        ClientApp.service(prompt);
        return;
      } else {
        System.out.println("비밀번호가 틀렸습니다!");
        return;
      }
    }

    System.out.println("등록되지 않은 사용자입니다!");
  }

}
