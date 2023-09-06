package project.controller;

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
    String memberNewId = prompt.inputString("아이디: ");

    NewMember newmember = newmemberDao.findByNewId(memberNewId);

    if (newmember != null) {
      String memberNewpw = prompt.inputString("비밀번호: ");
      if (newmember.getNewpassword().equals(memberNewpw)) {
        System.out.println("아이디: " + newmember.getNewid());

        String newId = prompt.inputString(" > ");
        if (!newId.equals(newmember.getNewid()) && newmemberDao.isExistingMember(newId)) {
          System.out.println("사용할 수 없는 아이디입니다!");
          return;
        }

        // 변경된 정보를 `newmember` 객체에 저장
        newmember.setNewid(newId);
        System.out.println("비밀번호: " + newmember.getNewpassword());
        newmember.setNewpassword(prompt.inputString(" > "));
        System.out.println("H.P: " + newmember.getNewphonenumber());
        newmember.setNewphonenumber(prompt.inputString(" > "));

        // 변경된 정보를 데이터베이스에 업데이트
        int rowsAffected = newmemberDao.update(newmember);
        if (rowsAffected > 0) {
          System.out.println("정보가 성공적으로 변경되었습니다.");
        } else {
          System.out.println("정보 변경에 실패하였습니다.");
        }
      } else {
        System.out.println("비밀번호가 틀렸습니다!");
      }
    } else {
      System.out.println("등록되지 않은 사용자입니다!");
    }
  }
}


