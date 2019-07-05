package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class App3 {
  static Scanner keyScan;

  public static void main(String[] args) {
    java.io.InputStream keyboard = System.in;
    keyScan = new Scanner(keyboard);

    Board[] boards = new Board[100];


    int i = 0;

    for (; i < boards.length; i++) {
      Board board = new Board();
      board.no = getIntValue("번호? ");
      board.contents = getStringValue("내용? ");
      board.createDate = getDateValue("작성일? ");

      boards[i] = board;

      System.out.print("계속 입력하시겠습니까?(Y/n)");
      String response = keyScan.nextLine();

      if (response.equals("n")) {
        break;
      }
    }

    for (int i2 = 0; i2 <= i; i2++) {
      Board board = boards[i2];
      System.out.printf("%s, %s, %s\n", board.no, board.contents, board.createDate);
    }
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
