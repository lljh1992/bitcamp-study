package project.controller;

import java.util.List;
import project.util.BreadcrumbPrompt;
import project.vo.NewMember;

public class NewMemberAddListener extends AbstractNewMemberListener {

  public NewMemberAddListener(List<NewMember> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    NewMember nm = new NewMember();

    String newMemberid = prompt.inputString("아이디: ");

    if (isExistingMember(newMemberid)) {
      System.out.println("해당 계정을 사용할 수 없습니다!");
      return;
    }

    nm.setNewid(newMemberid);
    nm.setNewpassword(prompt.inputString("비밀번호: "));
    nm.setNewname(prompt.inputString("이름: "));
    nm.setNewphonenumber(prompt.inputString("H.P: "));

    this.list.add(nm);

  }
}
