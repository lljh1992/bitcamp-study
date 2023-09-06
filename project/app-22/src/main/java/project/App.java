/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package project;

import project.controller.NewMemberHandler;
import project.util.ArrayList;
import project.util.MenuPrompt;

public class App {

  public static void main(String[] args) {

    MenuPrompt prompt = new MenuPrompt();

    NewMemberHandler newMemberHandler = new NewMemberHandler(prompt, "로비", new ArrayList());

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
