package project.controller;

import java.util.List;
import project.dao.MemberDao;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.vo.Member;

public class NewMemberListListener implements ActionListener {

  MemberDao memberDao;

  public NewMemberListListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("----------------------");
    System.out.println("번호 | 아이디 | 이름 ");
    System.out.println("----------------------");

    List<Member> list = memberDao.list();

    for (Member member : list) {
      System.out.printf("%d, %s, %s\n", member.getNo(), member.getId(), member.getName());
    }
  }

}
