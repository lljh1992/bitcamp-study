package project.handler;

import project.dao.NewMemberDao;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.vo.NewMember;

public class NewMemberAddListener implements ActionListener {

  NewMemberDao newmemberDao;

  public NewMemberAddListener(NewMemberDao newmemberDao) {
    this.newmemberDao = newmemberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    NewMember nm = new NewMember();

    String newMemberid = prompt.inputString("아이디: ");

    if (newmemberDao.isExistingMember(newMemberid)) {
      System.out.println("해당 계정을 사용할 수 없습니다!");
      return;
    }
    nm.setNewid(newMemberid);
    nm.setNewpassword(prompt.inputString("비밀번호: "));
    nm.setNewname(prompt.inputString("이름: "));
    nm.setNewphonenumber(prompt.inputString("H.P: "));
    newmemberDao.insert(nm);
  }
}
