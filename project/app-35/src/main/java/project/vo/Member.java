package project.vo;

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
  private List<Long> entryTimes;
  private List<Long> exitTimes;


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



  public List<Long> getEntryTimes() {
    return entryTimes;
  }

  public void setEntryTimes(List<Long> entryTimes) {
    this.entryTimes = entryTimes;
  }

  public List<Long> getExitTimes() {
    return exitTimes;
  }

  public void setExitTimes(List<Long> exitTimes) {
    this.exitTimes = exitTimes;
  }

  // 입차 시간 추가 메서드
  public void addEntryTime(long entryTime) {
    entryTimes.add(System.currentTimeMillis());
  }

  // 출차 시간 추가 메서드
  public void addExitTime(long exitTime) {
    exitTimes.add(System.currentTimeMillis());
  }

  // 입차 시간 출력 메서드
  public void printEntryTimes() {
    for (Long entryTime : entryTimes) {
      System.out.printf("차량 입차 시간: %tY-%<tm-%<td %<tT%n", entryTime);
      System.out.println();
    }
  }

  // 출차 시간 출력 메서드
  public void printExitTimes() {
    for (Long exitTime : exitTimes) {
      System.out.printf("차량 출차 시간: %tY-%<tm-%<td %<tT%n", exitTime);
      System.out.println();
    }
  }

  // 타임스탬프를 읽기 쉬운 문자열 형태로 변환하는 도우미 메서드
  // private String convertTimestampToString(long timestamp) {
  // return String.valueOf(timestamp);
  // }


  public boolean isInside() {
    return isInside;
  }

  public void setInside(boolean isInside) {
    this.isInside = isInside;
  }



}
