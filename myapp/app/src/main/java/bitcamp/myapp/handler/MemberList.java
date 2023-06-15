package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Member;

public class MemberList {
  private static final int MAX_SIZE = 100;

  private Member[] members = new Member[MAX_SIZE];
  private int length;

  public boolean add(Member m) {
    if (this.length == members.length) {

    }
    this.members[this.length++] = m;
    return true;
  }

  public Member[] list() {
    Member[] arr = new Member[this.length];
    for (int i = 0; i < this.length; i++) {
      arr[i] = this.members[i];
    }
    return arr;
  }

  public Member get(int no) {
    for (int i = 0; i < this.length; i++) {
      Member m = this.members[i];
      if (m.getNo() == no) {
        return m;
      }
    }
    return null;
  }

  public boolean delete(int no) {
    int deleteIndex = indexOf(no);
    if (deleteIndex == -1) {
      return false;
    }

    for (int i = deleteIndex; i < this.length; i++) {
      this.members[i] = this.members[i + 1];
    }
    this.members[--this.length] = null;
    return true;
  }

  private int indexOf(int memberNo) {
    for (int i = 0; i < this.length; i++) {
      Member m = this.members[i];
      if (m.getNo() == memberNo) {
        return i;
      }
    }
    return -1;
  }
}
