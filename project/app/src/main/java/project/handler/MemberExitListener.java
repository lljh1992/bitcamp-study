package project.handler;

import java.util.List;
import project.dao.MemberDao;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.util.JsonDataHelper;
import project.vo.Member;

public class MemberExitListener implements ActionListener {

  MemberDao memberDao;

  public MemberExitListener(MemberDao memberDao) {
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
        System.out.println(" 차량 출차 기록 ");
        System.out.println("------------------------------");
        vehicleFound = true;
        member.getExitTimes();
        member.printExitTimes();
        memberDao.saveExit(member);
        break; // 차량번호가 중복되지 않기 때문에 루프 종료
      }
    }
    if (!vehicleFound) {
      System.out.println("등록된 차량이 아닙니다.");
    }
    JsonDataHelper.saveJson(recordVehicle, list);
  }


}
