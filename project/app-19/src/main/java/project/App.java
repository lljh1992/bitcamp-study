/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package project;

import project.controller.NewMemberHandler;
import util.Prompt;

public class App {

  public static void main(String[] args) {

    Prompt prompt = new Prompt();

    NewMemberHandler newMemberHandler = new NewMemberHandler(prompt, "로비");
    // MemberHandler memberHandler = new MemberHandler(prompt, "입주자");
    // LoginHandler loginHandler = new LoginHandler(prompt, "민원사항");
    // BoardHandler boardHandler = new BoardHandler(prompt, "공지사항");

    printTitle();

    newMemberHandler.execute();

    prompt.close();
  }

  static void printTitle() {
    System.out.println("--------------------------");
    System.out.println("     주차 관리 시스템     ");
    System.out.println("--------------------------");
  }

}
