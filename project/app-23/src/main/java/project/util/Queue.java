package project.util;

public class Queue extends LinkedList {
  public static void main(String[] args) {

  }

  public void offer(Object value) {
    this.add(value);
  }

  public Object poll() {
    if (this.size() == 0) {
      return null;
    }
    return this.remove(0);
  }
}
