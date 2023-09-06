package project.controller;

import project.util.BreadcrumbPrompt;
import project.util.List;
import project.vo.NewMember;

public class NewMemberLoginListener extends AbstractNewMemberListener {

  LoginHandler LoginHandler = new LoginHandler();

  public NewMemberLoginListener(List list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    String memberNewId = prompt.inputString("아이디: ");
    String memberNewPw = prompt.inputString("비밀번호: ");


    for (int i = 0; i < this.list.size(); i++) {
      NewMember nm = (NewMember) this.list.get(i);
      if (nm != null && nm.getNewid().equals(memberNewId)) {
        if (nm.getNewpassword().equals(memberNewPw)) {
          LoginHandler.service(prompt);
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
