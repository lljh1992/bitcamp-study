package project.vo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Member implements Serializable {

  private static final long serialVersionUID = 1L;

  public static final char RESIDENT = 'Y';
  public static final char OUTSIDER = 'N';

  private int no;
  private String building;
  private String name;
  private String phonenumber;
  private String password;
  private String carnumber;
  // private String VehicleOwnership;
  // private char residencestatus;
  private Date createdDate;

  @Override
  public int hashCode() {
    return Objects.hash(no);
  }

  // 입출차 기록
  private boolean isInside;
  private List<Timestamp> entryTimes;
  private List<Timestamp> exitTimes;


  public Member() {
    // this.no = userId++;
    this.isInside = false;
    this.entryTimes = new ArrayList<>();
    this.exitTimes = new ArrayList<>();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Member other = (Member) obj;
    return no == other.no;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getBuilding() {
    return building;
  }

  public void setBuilding(String building) {
    this.building = building;
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

  // public String getVehicleOwnership() {
  // return VehicleOwnership;
  // }

  // public void setVehicleOwnership(String vehicleOwnership) {
  // VehicleOwnership = vehicleOwnership;
  // }


  // public char getResidencestatus() {
  // return residencestatus;
  // }

  // public void setResidencestatus(char residencestatus) {
  // this.residencestatus = residencestatus;
  // }

  public List<Timestamp> getEntryTimes() {
    return entryTimes;
  }

  public void setEntryTimes(List<Timestamp> entryTimes) {
    this.entryTimes = entryTimes;
  }

  public List<Timestamp> getExitTimes() {
    return exitTimes;
  }

  public void setExitTimes(List<Timestamp> exitTimes) {
    this.exitTimes = exitTimes;
  }

  // 입차 시간 추가 메서드
  public void addEntryTime(Timestamp entryTime) {
    entryTimes.add(entryTime);
  }

  // 출차 시간 추가 메서드
  public void addExitTime(Timestamp exitTime) {
    exitTimes.add(exitTime);
  }

  // 입차 시간 출력 메서드
  public void printEntryTimes() {
    for (Timestamp entryTime : entryTimes) {
      System.out.printf("차량 입차 시간: %tY-%<tm-%<td %<tT%n", entryTime);
      System.out.println();
    }
  }

  // 출차 시간 출력 메서드
  public void printExitTimes() {
    for (Timestamp exitTime : exitTimes) {
      System.out.printf("차량 출차 시간: %tY-%<tm-%<td %<tT%n", exitTime);
      System.out.println();
    }
  }

  public boolean isInside() {
    return isInside;
  }

  public void setInside(boolean isInside) {
    this.isInside = isInside;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Object getInoutNo() {
    // TODO Auto-generated method stub
    return null;
  }



}
