package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App3 {
  static Scanner keyScan;

  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    keyScan = new Scanner(keyboard);

    int no = getIntValue("번호? ");
    String contents = getStringValue("내용? ");
    java.sql.Date createDate = getDateValue("작성일? ");

    System.out.println("번호: " + no);
    System.out.println("내용: " + contents);
    System.out.println("작성일: " + createDate);

  }

  private static Date getDateValue(String messsage) {
    while (true) {
      try {
        System.out.println(messsage);
        return java.sql.Date.valueOf(keyScan.nextLine());
      } catch (IllegalArgumentException e) {
        System.out.println("yyyy-MM-dd");
      }
    }
  }

  private static int getIntValue(String message) {
    while (true) {
      try {
        System.out.println(message);
        return Integer.parseInt(keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자를 입력하세요.");
      }
    }
  }

  private static String getStringValue(String message) {
    System.out.println(message);
    return keyScan.nextLine();
  }

}
