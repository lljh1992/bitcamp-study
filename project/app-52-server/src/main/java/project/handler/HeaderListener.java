package project.handler;

import project.util.ActionListener;
import project.util.BreadcrumbPrompt;

public class HeaderListener implements ActionListener {

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("====================[비트캠프!]==");
  }
}
