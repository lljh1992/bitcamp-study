package project.controller;

import project.dao.NewMemberDao;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.vo.NewMember;

public class NewMemberDeleteListener implements ActionListener {

  NewMemberDao newmemberDao;

  public NewMemberDeleteListener(NewMemberDao newmemberDao) {
    this.newmemberDao = newmemberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    String memberNewId = prompt.inputString("아이디: ");

    NewMember newmember = newmemberDao.findByNewId(memberNewId);

    if (newmember != null) {
      String memberNewpw = prompt.inputString("비밀번호: ");
      if (newmember.getNewpassword().equals(memberNewpw)) {
        String response = prompt.inputString("회원탈퇴를 진행하시겠습니까? (Y/n) ");
        if (response.equals("") || response.equalsIgnoreCase("Y")) {
          if (newmemberDao.deleteNew(newmember)) {
            System.out.println("회원탈퇴가 완료되었습니다.");
          } else {
            System.out.println("회원탈퇴에 실패하였습니다.");
          }
        } else {
          System.out.println("회원탈퇴가 취소되었습니다.");
        }
      } else {
        System.out.println("비밀번호가 틀렸습니다!");
      }
    } else {
      System.out.println("등록되지 않은 사용자입니다!");
    }

  }

}
