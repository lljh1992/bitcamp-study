package project.controller;

import project.util.ActionListener;
import project.util.BreadcrumbPrompt;

public class HelloListener implements ActionListener {
  @Override
  public void service(BreadcrumbPrompt prompt) {
    String name = prompt.inputString("이름은?");
    System.out.printf("%s 님 반가워요!\n", name);
  }
}
