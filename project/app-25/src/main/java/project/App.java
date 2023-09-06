/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package project;

import project.controller.FooterListener;
import project.controller.HeaderListener;
import project.controller.HelloListener;
import project.controller.NewMemberAddListener;
import project.controller.NewMemberDeleteListener;
import project.controller.NewMemberListListener;
import project.controller.NewMemberLoginListener;
import project.controller.NewMemberUpdateListener;
import project.util.ArrayList;
import project.util.BreadcrumbPrompt;
import project.util.Menu;
import project.util.MenuGroup;
import project.vo.NewMember;

public class App {

  public static void main(String[] args) {

    ArrayList<NewMember> newmemberList = new ArrayList<>();

    BreadcrumbPrompt prompt = new BreadcrumbPrompt();

    MenuGroup mainMenu = new MenuGroup("메인");

    MenuGroup newmemberMenu = new MenuGroup("주차 관리 시스템");
    newmemberMenu.add(new Menu("회원가입", new NewMemberAddListener(newmemberList)));
    newmemberMenu.add(new Menu("로그인", new NewMemberLoginListener(newmemberList)));
    newmemberMenu.add(new Menu("회원정보 변경", new NewMemberUpdateListener(newmemberList)));
    newmemberMenu.add(new Menu("회원정보 조회", new NewMemberListListener(newmemberList)));
    newmemberMenu.add(new Menu("회원탈퇴", new NewMemberDeleteListener(newmemberList)));
    mainMenu.add(newmemberMenu);

    Menu helloMenu = new Menu("안녕하세요.");
    helloMenu.addActionListener(new HeaderListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FooterListener());
    mainMenu.add(helloMenu);

    printTitle();

    mainMenu.execute(prompt);

    prompt.close();
  }

  static void printTitle() {
    System.out.println("--------------------------");
    System.out.println("     주차 관리 시스템     ");
    System.out.println("--------------------------");
  }

}
