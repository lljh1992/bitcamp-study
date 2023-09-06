package project.controller;

import java.io.IOException;
import java.util.List;
import project.dao.MemberDao;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.util.Component;
import project.vo.Member;

@Component("/member/entry")
public class MemberEntryListener implements ActionListener {

  MemberDao memberDao;

  public MemberEntryListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    String recordVehicle = prompt.inputString("차량번호: ");
    boolean vehicleFound = false;

    List<Member> list = memberDao.findAll();

    for (Member member : list) {
      if (member.getCarnumber().equals(recordVehicle)) {
        prompt.println("------------------------------");
        prompt.printf(" %s             차량 입차 기록\n", recordVehicle);
        prompt.println("------------------------------");
        vehicleFound = true;
        member.getEntryTimes();
        member.printEntryTimes();
        memberDao.saveEntry(member); // 차량번호에 해당하는 멤버의 입차 시간을 저장합니다.

      }
    }

    if (!vehicleFound) {
      prompt.println("등록된 차량이 아닙니다.");

    }
  }
}


