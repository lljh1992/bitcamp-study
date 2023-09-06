package project.controller;

import java.util.List;
import project.App;
import project.util.BreadcrumbPrompt;
import project.vo.NewMember;

public class NewMemberLoginListener extends AbstractNewMemberListener {

  public NewMemberLoginListener(List<NewMember> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    String memberNewId = prompt.inputString("아이디: ");
    String memberNewPw = prompt.inputString("비밀번호: ");


    for (int i = 0; i < this.list.size(); i++) {
      NewMember nm = this.list.get(i);
      if (nm != null && nm.getNewid().equals(memberNewId)) {
        if (nm.getNewpassword().equals(memberNewPw)) {
          App.service(prompt);
          // LoginHandler.service(prompt);
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
