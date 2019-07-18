package com.eomcs.util;

public class Node {
  Object value;
  Node next; // 다음 상자를 가리키는 주소 변수
  
  public Node() {
  }
  
  public Node(Object value) {
    this.value = value;
  }
}
