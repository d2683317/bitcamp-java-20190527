package com.eomcs.util;

public class LinkedListTest {
  public static void main(String[] args) {
    LinkedList list = new LinkedList();
    list.add("aaa");
    list.add("bbb");
    list.add("ccc");
    list.add("ddd");
    list.add("eee");
    list.add("fff");
    
    
    list.set(0, "xxx");
    
    for(int i = 0; i<list.size; i++) {
      System.out.println(list.get(i));
    }
    
    list.remove(0);
    list.remove(4);
    list.remove(0);
    list.remove(0);
    list.remove(0);
    list.remove(0);
    
    list.add("okok");
    list.add("nono");
    list.clear();
    
    
    System.out.println("----------------------");
    
    for(int i = 0; i<list.size; i++) {
      System.out.println(list.get(i));
    }
  }
}
