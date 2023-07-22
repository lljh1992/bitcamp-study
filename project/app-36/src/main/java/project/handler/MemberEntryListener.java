package project.handler;

import java.util.List;
import project.dao.MemberDao;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.util.JsonDataHelper;
import project.vo.Member;

public class MemberEntryListener implements ActionListener {

  MemberDao memberDao;

  public MemberEntryListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    String recordVehicle = prompt.inputString("차량번호: ");
    boolean vehicleFound = false;

    List<Member> list = memberDao.list();

    for (Member member : list) {
      if (member.getCarnumber().equals(recordVehicle)) {
        System.out.println("------------------------------");
        System.out.printf(" %s             차량 입차 기록\n", recordVehicle);
        System.out.println("------------------------------");
        vehicleFound = true;
        member.getEntryTimes();
        member.printEntryTimes();
        memberDao.saveEntry(member); // 차량번호에 해당하는 멤버의 입차 시간을 저장합니다.
      }
    }

    if (!vehicleFound) {
      System.out.println("등록된 차량이 아닙니다.");
    }
    JsonDataHelper.saveJson(recordVehicle, list);
  }
}



