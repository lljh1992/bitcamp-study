package project.vo;

import java.sql.Timestamp;

public class ParkingTime {
  private int no;
  private String carnumber;
  private Timestamp entryTime;
  private Timestamp exitTime;

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getCarnumber() {
    return carnumber;
  }

  public void setCarnumber(String carnumber) {
    this.carnumber = carnumber;
  }

  public Timestamp getEntryTime() {
    return entryTime;
  }

  public void setEntryTime(Timestamp entryTime) {
    this.entryTime = entryTime;
  }

  public Timestamp getExitTime() {
    return exitTime;
  }

  public void setExitTime(Timestamp exitTime) {
    this.exitTime = exitTime;
  }



}
