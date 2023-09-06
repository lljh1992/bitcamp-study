package project.controller;

import project.util.BreadcrumbPrompt;
import project.util.List;
import project.vo.NewMember;

public class NewMemberListListener extends AbstractNewMemberListener {

  public NewMemberListListener(List list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("----------------------");
    System.out.println("번호 | 아이디 | 이름 ");
    System.out.println("----------------------");

    for (int i = 0; i < this.list.size(); i++) {
      NewMember nm = (NewMember) this.list.get(i);
      System.out.printf("%d, %s, %s\n", nm.getNewno(), nm.getNewid(), nm.getNewname());
    }
  }

}
