package project.controller;

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
    boolean vehicleFound = false;

    for (Member member : this.list) {
      if (member.getCarnumber().equals(recordVehicle)) {
        System.out.println("------------------------------");
        System.out.println(" 차량 입차 기록 ");
        System.out.println("------------------------------");

        vehicleFound = true;
        long entryTime = System.currentTimeMillis();
        member.addEntryTime(entryTime);
        member.printEntryTimes();
        break; // 차량번호가 중복되지 않기 때문에 루프 종료
      }
    }

    if (!vehicleFound) {
      System.out.println("등록된 차량이 아닙니다.");
    }
  }


}
