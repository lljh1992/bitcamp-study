package project.controller;

import java.util.List;
import project.dao.NewMemberDao;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.vo.NewMember;

public class NewMemberUpdateListener implements ActionListener {

  NewMemberDao newmemberDao;

  public NewMemberUpdateListener(NewMemberDao newmemberDao) {
    this.newmemberDao = newmemberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    String memberNewId = prompt.inputString("아이디 : ");

    List<NewMember> list = newmemberDao.list();

    for (NewMember newmember : list) {
      if (newmember.getNewid().equals(memberNewId)) {
        String memberNewpw = prompt.inputString("비밀번호 : ");
        if (newmember.getNewpassword().equals(memberNewpw)) {
          System.out.printf("아이디: %s", newmember.getNewid());

          String newId = prompt.inputString(" > ");
          if (newmemberDao.isExistingMember(newId)) {
            System.out.println("사용할 수 없는 아이디입니다!");
            return;
          }
          newmember.setNewid(newId);

          System.out.printf("비밀번호: %s", newmember.getNewpassword());
          newmember.setNewpassword(prompt.inputString(" > "));
          System.out.printf("H.P: %s", newmember.getNewphonenumber());
          newmember.setNewphonenumber(prompt.inputString(" > "));
          return;
        } else {
          System.out.println("비밀번호가 틀렸습니다!");
          return;
        }
      }
    }
    System.out.println("등록되지 않은 사용자입니다!");
  }
}
