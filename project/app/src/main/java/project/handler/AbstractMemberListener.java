package project.handler;

import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.util.List;
import project.vo.Member;

public abstract class AbstractMemberListener implements ActionListener {

  protected List list;

  public AbstractMemberListener(List list) {
    this.list = list;
  }

  protected static String toResidentString(char type) {
    return type == 'Y' ? "거주자" : "외부인";
  }

  protected char inputResident(char type, BreadcrumbPrompt prompt) {
    String label;
    if (type == 0) {
      label = "거주 여부?\n";
    } else {
      label = String.format("거주 여부(%s)?\n", toResidentString(type));
    }

    while (true) {
      String live = prompt.inputString(label + " 1. 거주자\n" + " 2. 외부인\n" + " > ");

      switch (live) {
        case "1":
          return Member.RESIDENT;
        case "2":
          return Member.OUTSIDER;
        default:
          System.out.println("유효한 번호를 입력하세요.");
      }
    }
  }

  protected Member findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Member m = (Member) this.list.get(i);
      if (m.getNo() == no) {
        return m;
      }
    }
    return null;
  }

}
