// 상속 문법을 이용하여 큐 만들기
package com.eomcs.util.step3;

import com.eomcs.util.Iterator;
import com.eomcs.util.LinkedList;

public class Queue<E> extends LinkedList<E> implements Cloneable {

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
  public Iterator<E> createIterator() {

    // 특정 메서드 안에서만 사용되는 클래스라면 메서드 안에 선언하라!
    // 이렇게 메서드 안에 선언된 중첩 클래스를 "local class"라 부른다.
    class QueueIterator implements Iterator<E> {
      @Override
      public boolean hasNext() {
        return size() > 0;
      }

      @Override
      public E next() {
        return poll();
      }
    }
    return new QueueIterator();
  }


}
