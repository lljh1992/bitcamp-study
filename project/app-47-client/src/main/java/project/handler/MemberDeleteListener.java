package project.handler;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import project.dao.MemberDao;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.vo.Member;

public class MemberDeleteListener implements ActionListener {

  MemberDao memberDao;

  public MemberDeleteListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  // @Override
  // public void service(BreadcrumbPrompt prompt) {
  // if (memberDao.delete(prompt.inputInt("번호? ")) == 0) {
  // System.out.println("해당 번호의 회원이 없습니다!");
  // }
  // }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    String memberId = prompt.inputString("아이디: ");

    Member member = memberDao.findByNewId(memberId);

    if (member != null) {
      String memberpw = prompt.inputString("비밀번호: ");

      // 입력한 비밀번호를 SHA-1 알고리즘을 사용하여 해시로 변환
      String hashedPassword = hashPassword(memberpw);

      if (member.getPassword().equals(hashedPassword)) {
        String response = prompt.inputString("회원탈퇴를 진행하시겠습니까? (Y/n) ");
        if (response.equals("") || response.equalsIgnoreCase("Y")) {
          if (memberDao.deleteNew(member)) {
            System.out.println("회원탈퇴가 완료되었습니다.");
          } else {
            System.out.println("회원탈퇴에 실패하였습니다.");
          }
        } else {
          System.out.println("회원탈퇴가 취소되었습니다.");
        }
      } else {
        System.out.println("비밀번호가 틀렸습니다!");
      }
    } else {
      System.out.println("등록되지 않은 사용자입니다!");
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
