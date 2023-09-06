package project.controller;

import java.time.LocalDateTime;
import java.util.List;
import project.util.BreadcrumbPrompt;
import project.vo.Member;

public class MemberEntryListener extends AbstractMemberListener {

  public MemberEntryListener(List<Member> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    String recordVehicle = prompt.inputString("차량번호: ");
    boolean found = false;

    for (int i = 0; i < this.list.size(); i++) {
      Member m = this.list.get(i);
      if (m.getCarnumber().equals(recordVehicle)) {
        printEntryTime();
        found = true;
        LocalDateTime entryTime = LocalDateTime.now();
        m.addEntryTime(entryTime);
        m.printEntryTimes();
      }
    }
    if (!found) {
      System.out.println("등록된 차량이 아닙니다.");
    }
  }

  private void printEntryTime() {
    System.out.println("------------------------------");
    System.out.println("        차량 입차 기록        ");
    System.out.println("------------------------------");

  }

}
