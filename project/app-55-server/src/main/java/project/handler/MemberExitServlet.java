package project.handler;

// @Component("/member/exit")
// public class MemberExitServlet implements ActionListener {
//
// MemberDao memberDao;
//
// public MemberExitServlet(MemberDao memberDao) {
// this.memberDao = memberDao;
// }
//
// @Override
// public void service(BreadcrumbPrompt prompt) throws IOException {
// String recordVehicle = prompt.inputString("차량번호: ");
// boolean vehicleFound = false;
//
// List<Member> list = memberDao.findAll();
//
// for (Member member : list) {
// if (member.getCarnumber().equals(recordVehicle)) {
// prompt.println("------------------------------");
// prompt.printf(" %s 차량 출차 기록\n", recordVehicle);
// prompt.println("------------------------------");
// vehicleFound = true;
// member.getExitTimes();
// // member.printExitTimes();
// memberDao.saveExit(member);
//
// }
// }
// if (!vehicleFound) {
// prompt.println("등록된 차량이 아닙니다.");
// }
// }
// }
