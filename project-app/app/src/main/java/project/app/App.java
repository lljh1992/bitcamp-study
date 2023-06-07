package project.app;


import project.app.handler.MemberHandler;
import project.util.Prompt;


public class App {
    
  public static void main(String[] args) {
    
    printTitle();
    
    while (MemberHandler.available()) {
      MemberHandler.inputMember();
      if (!promptContinue()) {
        break;
      }
    }
    
    MemberHandler.pirntMembers();

    Prompt.close();
  }

  static void printTitle() {
    System.out.println("--------------------------");
    System.out.println("     주차 관리 시스템     ");
    System.out.println("--------------------------");
  }
  
  static boolean promptContinue() {
    String response = Prompt.inputString("등록을 계속 하시겠습니까?(Y/n) ");
    if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
      return false;
    }
    return true;
  }
}
