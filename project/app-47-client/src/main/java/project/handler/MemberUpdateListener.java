package project.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import project.dao.MemberDao;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.vo.Member;

public class MemberUpdateListener implements ActionListener {

  MemberDao memberDao;

  public MemberUpdateListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    String memberId = prompt.inputString("아이디: ");
    Member m = memberDao.findByNewId(memberId);


    if (m != null) {
      String memberpw = prompt.inputString("비밀번호: ");

      // 입력한 비밀번호를 SHA-1 알고리즘을 사용하여 해시로 변환
      String hashedPassword = hashPassword(memberpw);

      if (m.getPassword().equals(hashedPassword)) {
        String newId = prompt.inputString("변경할 아이디를 입력하세요(%s) > ", m.getId());
        if (!newId.equals(m.getId()) && memberDao.isExistingMember(newId)) {
          System.out.println("사용할 수 없는 아이디입니다!");
          return;
        } else {

          // 변경된 정보를 `member` 객체에 저장
          m.setId(newId);
          m.setPassword(prompt.inputString("변경할 비밀번호를 입력하세요 > "));
          m.setBuilding(prompt.inputString("동(%s) > ", m.getBuilding()));
          m.setUnit(prompt.inputString("호수(%s) > ", m.getUnit()));
          m.setName(prompt.inputString("이름(%s) > ", m.getName()));
          m.setPhonenumber(prompt.inputString("H.P(%s) > ", m.getPhonenumber()));
          m.setCarnumber(prompt.inputString("차량 번호(%s) > ", m.getCarnumber()));
          m.setVehicleOwnership(prompt.inputString("차량 보유 현황(%s) > ", m.getVehicleOwnership()));
          m.setResidencestatus(MemberActionListener.inputResident(m.getResidencestatus(), prompt));
          memberDao.update(m);
        }
      }
    }
  }

  // SHA-1 알고리즘을 사용하여 비밀번호를 해시로 변환하는 메서드
  private String hashPassword(String password) {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
      byte[] hashBytes = messageDigest.digest(password.getBytes());

      StringBuilder stringBuilder = new StringBuilder();
      for (byte hashByte : hashBytes) {
        stringBuilder.append(Integer.toString((hashByte & 0xff) + 0x100, 16).substring(1));
      }

      return stringBuilder.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }
}
