package project.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import project.ClientApp;
import project.dao.MemberDao;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.vo.Member;

public class MemberLoginListener implements ActionListener {

  MemberDao memberDao;

  public MemberLoginListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  // @Override
  // public void service(BreadcrumbPrompt prompt) {
  // String memberNewId = prompt.inputString("아이디: ");
  // String memberNewPw = prompt.inputString("비밀번호: ");
  //
  // if (newmemberDao.isExistingMember(memberNewId)) {
  // NewMember newmember = newmemberDao.findByNewId(memberNewId);
  // if (newmember != null && (newmember.getNewpassword()).equals(memberNewPw)) {
  // ClientApp.service(prompt);
  // return;
  // } else {
  // System.out.println("비밀번호가 틀렸습니다!");
  // return;
  // }
  // }
  //
  // System.out.println("등록되지 않은 사용자입니다!");
  // }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    String memberId = prompt.inputString("아이디: ");
    String memberPw = prompt.inputString("비밀번호: ");

    if (memberDao.isExistingMember(memberId)) {
      Member member = memberDao.findByNewId(memberId);
      if (member != null && validatePassword(memberPw, member.getPassword())) {
        ClientApp.service(prompt);
        return;
      } else {
        System.out.println("비밀번호가 틀렸습니다!");
        return;
      }
    }

    System.out.println("등록되지 않은 사용자입니다!");
  }

  // 비밀번호를 검증하는 메서드
  private boolean validatePassword(String enteredPassword, String storedHashedPassword) {
    String hashedEnteredPassword = hashPassword(enteredPassword);
    return hashedEnteredPassword.equals(storedHashedPassword);
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
