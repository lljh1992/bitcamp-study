package project.vo;

import java.io.Serializable;
import java.sql.Date;
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
  private Date createdDate;
  private String photo;

  @Override
  public String toString() {
    return "Member{" +
            "no=" + no +
            ", building='" + building + '\'' +
            ", name='" + name + '\'' +
            ", phonenumber='" + phonenumber + '\'' +
            ", password='" + password + '\'' +
            ", carnumber='" + carnumber + '\'' +
            ", createdDate=" + createdDate +
            ", photo='" + photo + '\'' +
            ", parkingTimes=" + parkingTimes +
            '}';
  }

  @Override
  public int hashCode() {
    return Objects.hash(no);
  }

  // 입출차 기록
  public List<ParkingTime> parkingTimes;

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCarnumber() {
    return carnumber;
  }

  public void setCarnumber(String carnumber) {
    this.carnumber = carnumber;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public List<ParkingTime> getParkingTimes() {
    return parkingTimes;
  }

  public void setParkingTimes(List<ParkingTime> parkingTimes) {
    this.parkingTimes = parkingTimes;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }


}
