// 상속 문법을 이용하여 큐 만들기
package com.eomcs.util;

public class Queue<E> extends LinkedList<E> implements Cloneable, Iterable<E> {

  @Override
  public Queue<E> clone() throws CloneNotSupportedException {
    Queue<E> temp = new Queue<>();
    for (int i = 0; i < size(); i++) {
      temp.offer(get(i));
    }
    return temp;
  }

  public void offer(E value) {
    add(value);
  }

  public E poll() {
    return remove(0);
  }

  public boolean empty() {
    return size() == 0;
  }

  // 큐의 데이터를 꺼내줄 Iterator를 제공한다.
  @Override
  public Iterator<E> iterator() {

    // 중첩 클래스를 정의한 후 인스턴스를 딱 한 개 생성하는 용도로 사용한다면
    // 굳이 클래스 이름을 가질 필요가 없다.
    // 클래스를 정의하자마자 바로 인스턴스를 만들어 사용하면 편하다.
    // 이렇게 정의하는 중첩 클래스를 "anonymous class"라 부른다.
    //
    return new Iterator<E>() {
      @Override
      public boolean hasNext() {
        return size() > 0;
      }

      @Override
      public E next() {
        return poll();
      }
    };
  }


}
