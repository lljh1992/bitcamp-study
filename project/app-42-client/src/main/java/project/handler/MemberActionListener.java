package project.controller;

import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.vo.Member;

public interface MemberActionListener extends ActionListener {



  static char inputResident(char type, BreadcrumbPrompt prompt) {
    String label;
    if (type == 0) {
      label = "거주 여부?\n";
    } else {
      label = String.format("거주 여부(%s)?\n", type == 'Y' ? "거주자" : "외부인");
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

}
