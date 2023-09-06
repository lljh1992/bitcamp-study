package project.controller;

import project.util.BreadcrumbPrompt;
import project.util.List;
import project.vo.NewMember;

public class NewMemberUpdateListener extends AbstractNewMemberListener {

  public NewMemberUpdateListener(List list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    String memberNewId = prompt.inputString("아이디 : ");

    for (int i = 0; i < this.list.size(); i++) {
      NewMember nm = (NewMember) this.list.get(i);
      // Object[] arr = this.list.toArray();
      // for (Object obj : arr) {
      // NewMember nm = (NewMember) obj;
      if (nm.getNewid().equals(memberNewId)) {
        String memberNewpw = prompt.inputString("비밀번호 : ");
        if (nm.getNewpassword().equals(memberNewpw)) {
          System.out.printf("아이디: %s", nm.getNewid());

          String newId = prompt.inputString(" > ");
          if (isExistingMember(newId)) {
            System.out.println("사용할 수 없는 아이디입니다!");
            return;
          }
          nm.setNewid(newId);

          System.out.printf("비밀번호: %s", nm.getNewpassword());
          nm.setNewpassword(prompt.inputString(" > "));
          System.out.printf("H.P: %s", nm.getNewphonenumber());
          nm.setNewphonenumber(prompt.inputString(" > "));
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
