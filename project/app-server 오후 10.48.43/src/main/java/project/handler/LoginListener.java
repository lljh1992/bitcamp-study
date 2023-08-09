package project.handler;

import java.io.IOException;
import project.dao.MemberDao;
import project.util.BreadcrumbPrompt;
import project.vo.Member;

public class LoginListener implements MemberActionListener {

  MemberDao memberDao;

  public LoginListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    while (true) {
      Member m = new Member();
      m.setPhonenumber(prompt.inputString("아이디: "));
      m.setPassword(prompt.inputString("비밀번호: "));

      Member loginUser = memberDao.findByPhonenumberAndPassword(m);
      if (loginUser == null) {
        System.out.println("회원 정보가 일치하지 않습니다.");
      } else {
        prompt.setAttribute("loginUser", loginUser);
        break;
      }
      prompt.end();
    }
  }
}
