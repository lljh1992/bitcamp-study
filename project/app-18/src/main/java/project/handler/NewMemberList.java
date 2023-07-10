package project.handler;

import vo.NewMember;

public class NewMemberList {

  private static final int DEFAULT_NEWSIZE = 3;
  private NewMember[] nmember = new NewMember[DEFAULT_NEWSIZE];
  private int newlength;

  public boolean add(NewMember nm) {
    if (this.newlength == nmember.length) {
      increase();
    }

    this.nmember[this.newlength++] = nm;
    return true;
  }

  private void increase() {
    NewMember[] arr = new NewMember[nmember.length + (nmember.length >> 1)];
    for (int i = 0; i < nmember.length; i++) {
      arr[i] = nmember[i];
    }
    nmember = arr;
    System.out.println("배열 확장: " + nmember.length);
  }

  public NewMember[] list() {
    // 리턴할 값을 담을 배열을 생성
    NewMember[] arr = new NewMember[this.newlength];

    // 원본 배열에서 입력된 인스턴스 주소를 꺼내
    // 새 배열에 담는다.
    for (int i = 0; i < this.newlength; i++) {
      arr[i] = this.nmember[i];
    }

    // 새 배열을 리턴한다.
    return arr;
  }

  public NewMember getStr(String newmemberId) {
    for (int i = 0; i < this.newlength; i++) {
      NewMember nm = this.nmember[i];
      if (nm.getNewid() == newmemberId) {
        return nm;
      }
    }
    return null;
  }

  public NewMember get(int no) {
    for (int i = 0; i < this.newlength; i++) {
      NewMember m = this.nmember[i];
      if (m.getNewno() == no) {
        return m;
      }
    }
    return null;
  }

  // private int indexOf(int memberNo) {
  // for (int i = 0; i < this.newlength; i++) {
  // NewMember m = this.nmember[i];
  // if (m.getNewno() == memberNo) {
  // return i;
  // }
  // }
  // return -1;
  // }

  boolean delete(String newmemberid) {
    int deletedIndex = loginindexOf(newmemberid);
    if (deletedIndex == -1) {
      return false;
    }
    for (int i = deletedIndex; i < this.newlength; i++) {
      this.nmember[i] = this.nmember[i + 1];
    }
    this.nmember[--this.newlength] = null;
    return true;
  }

  private int loginindexOf(String newmemberid) {
    NewMember[] arr = this.list();
    for (int i = 0; i < arr.length; i++) {
      NewMember nm = this.nmember[i];
      if (nm.getNewid().equals(newmemberid)) {
        return i;
      }
    }
    return -1;
  }

}
