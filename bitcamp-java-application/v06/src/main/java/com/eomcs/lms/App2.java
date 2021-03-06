package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {
  static Scanner keyScan;

  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    keyScan = new Scanner(keyboard);

    int[] no = new int[100];
    String[] name = new String[100];
    String[] email = new String[100];
    String[] pw = new String[100];
    String[] photo = new String[100];
    String[] phoneNum = new String[100];
    Date[] resisteredDate = new Date[100];

    int i = 0;
    for (; i < no.length; i++) {
      no[i] = getIntValue("번호? ");
      name[i] = getStringValue("이름? ");
      email[i] = getStringValue("이메일? ");
      pw[i] = getStringValue("암호? ");
      photo[i] = getStringValue("사진? ");
      phoneNum[i] = getStringValue("전화? ");
      resisteredDate[i] = getDateValue("가입일? ");

      System.out.println("계속 입력하시겠습니까?(Y/n) ");
      String response = keyScan.nextLine();
      if (response.equals("n")) {
        break;
      }
    }

    System.out.println();

    for (int i2 = 0; i2 <= i; i2++) {
      System.out.printf("%s, %s, %s, %s, %s\n", no[i2], name[i2], email[i2], phoneNum[i2],
          resisteredDate[i2]);
    }

  }

  private static int getIntValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return Integer.parseInt(keyScan.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("숫자를 입력하세요.");
      }
    }
  }

  private static String getStringValue(String message) {
    System.out.print(message);
    return keyScan.nextLine();
  }

  private static Date getDateValue(String message) {
    while (true) {
      try {
        System.out.print(message);
        return Date.valueOf(keyScan.nextLine());
      } catch (IllegalArgumentException e) {
        System.out.println("yyyy-MM-dd 형식으로 입력하세요.");
      }
    }
  }

}
