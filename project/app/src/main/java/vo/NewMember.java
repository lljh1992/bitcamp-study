package vo;

public class NewMember {

  private static int newuserId = 1;

  public int newno;
  public String newid;
  public String newpassword;
  public String newname;
  public String newphonenumber;

  public NewMember() {
    this.newno = newuserId++;
  }



  public int getNewno() {
    return newno;
  }
  public void setNewno(int newno) {
    this.newno = newno;
  }
  public String getNewid() {
    return newid;
  }
  public void setNewid(String newid) {
    this.newid = newid;
  }
  public String getNewpassword() {
    return newpassword;
  }
  public void setNewpassword(String newpassword) {
    this.newpassword = newpassword;
  }
  public String getNewname() {
    return newname;
  }
  public void setNewname(String newname) {
    this.newname = newname;
  }
  public String getNewphonenumber() {
    return newphonenumber;
  }
  public void setNewphonenumber(String newphonenumber) {
    this.newphonenumber = newphonenumber;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (this.getClass() != obj.getClass()) {
      return false;
    }
    NewMember nm = (NewMember) obj;

    if (this.getNewno() != nm.getNewno()) {
      return false;
    }
    return true;
  }
}

