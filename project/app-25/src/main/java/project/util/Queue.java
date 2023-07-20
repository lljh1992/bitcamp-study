package project.util;

public class Queue<E> extends LinkedList<E> {
  public static void main(String[] args) {

  }


  public void offer(E value) {
    this.add(value);
  }

  public E poll() {
    if (this.size() == 0) {
      return null;
    }
    return this.remove(0);
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<>() {
      @Override
      public boolean hasNext() {
        // TODO Auto-generated method stub
        return size() > 0;
      }

      @Override
      public E next() {
        return poll();
      }
    };
  }
}
