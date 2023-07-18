package vo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Member {

  private static int userId = 1;

  public static final char RESIDENT = 'Y';
  public static final char OUTSIDER = 'N';

  public int no;
  public String a;
  public String b;
  public String name;
  public String phonenumber;
  public String carnumber;
  public String VehicleOwnership;
  public char type;

  // 입출차 기록
  private boolean isInside;
  private List<LocalDateTime> entryTimes;
  private List<LocalDateTime> exitTimes;


  public Member() {
    this.no = userId++;
    this.isInside = false;
    this.entryTimes = new ArrayList<>();
    this.exitTimes = new ArrayList<>();
  }

  public Member(int no) {
    this.no = no;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (this.getClass() != obj.getClass()) {
      return false;
    }

    Member m = (Member) obj;

    if (this.getNo() != m.getNo()) {
      return false;
    }

    return true;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getA() {
    return a;
  }

  public void setA(String a) {
    this.a = a;
  }

  public String getB() {
    return b;
  }

  public void setB(String b) {
    this.b = b;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhonenumber() {
    return phonenumber;
  }

  public void setPhonenumber(String phonenumber) {
    this.phonenumber = phonenumber;
  }

  public String getCarnumber() {
    return carnumber;
  }

  public void setCarnumber(String carnumber) {
    this.carnumber = carnumber;
  }

  public String getVehicleOwnership() {
    return VehicleOwnership;
  }

  public void setVehicleOwnership(String vehicleOwnership) {
    VehicleOwnership = vehicleOwnership;
  }

  public char getType() {
    return type;
  }

  public void setType(char type) {
    this.type = type;
  }



  public List<LocalDateTime> getEntryTimes() {
    return entryTimes;
  }

  public void setEntryTimes(List<LocalDateTime> entryTimes) {
    this.entryTimes = entryTimes;
  }

  public List<LocalDateTime> getExitTimes() {
    return exitTimes;
  }

  public void setExitTimes(List<LocalDateTime> exitTimes) {
    this.exitTimes = exitTimes;
  }

  // 입차 시간 추가 메서드
  public void addEntryTime(LocalDateTime entryTime) {
    entryTimes.add(entryTime);
  }

  // 출차 시간 추가 메서드
  public void addExitTime(LocalDateTime exitTime) {
    exitTimes.add(exitTime);
  }

  // 입차 시간 출력 메서드
  public void printEntryTimes() {
    for (LocalDateTime entryTime : entryTimes) {
      System.out.printf("%s: 차량 입차 시간: " + entryTime, getCarnumber());
      System.out.println();
    }
  }

  // 출차 시간 출력 메서드
  public void printExitTimes() {
    for (LocalDateTime exitTime : exitTimes) {
      System.out.printf("%s: 차량 출차 시간: " + exitTime, getCarnumber());
      System.out.println();
    }
  }

  public boolean isInside() {
    return isInside;
  }

  public void setInside(boolean isInside) {
    this.isInside = isInside;
  }



}
