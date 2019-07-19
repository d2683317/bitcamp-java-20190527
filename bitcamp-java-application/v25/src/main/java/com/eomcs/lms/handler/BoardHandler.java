package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.List;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardHandler {

  private List<Board> boardList;
  private Input input;

  public BoardHandler(Input input, List<Board> list) {
    this.input = input;
    this.boardList = list;
  }

  public void addBoard() {
    Board board = new Board();

    board.setNo(input.getIntValue("번호? "));
    board.setContents(input.getStringValue("내용? "));
    board.setCreateDate(new Date(System.currentTimeMillis()));
    board.setViewCount(input.getIntValue("조회수? "));

    boardList.add(board);
    System.out.println("저장하였습니다.");
  }

  public void listBoard() {
    Board[] boards = boardList.toArray(new Board[] {});
    /*
     * Board[] boards = new Board[boardList.size()]; boardList.toArray(boards);
     */
    for (Board board : boards) {
      System.out.printf("%s, %s, %s\n", board.getNo(), board.getContents(), board.getCreateDate(),
          board.getViewCount());
    }
  }

  public void detailBoard() {
    int no = input.getIntValue("번호?");

    Board board = null;
    for (int i = 0; i < boardList.size(); i++) {
      Board temp = boardList.get(i);
      if (temp.getNo() == no) {
        board = temp;
        break;
      }
    }
    if (board == null) {
      System.out.println("해당 번호의 데이터가 없습니다!");
      return;
    }
    System.out.printf("내용: %s\n", board.getContents());
    System.out.printf("작성일: %s\n", board.getCreateDate());
  }

  public void updateBoard() {
    int no = input.getIntValue("번호?");

    Board board = null;
    for (int i = 0; i < boardList.size(); i++) {
      Board temp = boardList.get(i);
      if (temp.getNo() == no) {
        board = temp;
        break;
      }
    }
    if (board == null) {
      System.out.println("해당 번호의 데이터가 없습니다!");
      return;
    }

    String str = input.getStringValue("내용(" + board.getContents() + ")? ");
    if (str.length() > 0) {
      board.setContents(str);
    }
    System.out.println("데이터를 변경했습니다.");
  }

  public void deleteBoard() {
    int no = input.getIntValue("번호?");

    for (int i = 0; i < boardList.size(); i++) {
      Board temp = boardList.get(i);
      if (temp.getNo() == no) {
        boardList.remove(i);
        System.out.println("데이터를 삭제하였습니다.");
        return;
      }
    }
    System.out.println("해당 번호의 데이터가 없습니다!");
  }

}
