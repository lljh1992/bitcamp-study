package project.controller;

import project.util.BreadcrumbPrompt;
import project.util.List;
import project.vo.NewMember;

public class NewMemberDeleteListener extends AbstractNewMemberListener {

  public NewMemberDeleteListener(List<NewMember> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    String memberNewId = prompt.inputString("아이디: ");

    for (int i = 0; i < this.list.size(); i++) {
      NewMember nm = this.list.get(i);
      if (nm.getNewid().equals(memberNewId)) {
        String memberNewpw = prompt.inputString("비밀번호: ");
        if (nm.getNewpassword().equals(memberNewpw)) {
          String response = prompt.inputString("회원탈퇴를 진행하시겠습니까? (Y/n) ");
          if (response.equals("") || response.equalsIgnoreCase("Y")) {
            if (this.list.remove(nm)) {
              System.out.println("회원탈퇴가 완료되었습니다.");
            } else {
              System.out.println("회원탈퇴에 실패하였습니다.");
            }
          } else {
            System.out.println("회원탈퇴가 취소되었습니다.");
          }
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
