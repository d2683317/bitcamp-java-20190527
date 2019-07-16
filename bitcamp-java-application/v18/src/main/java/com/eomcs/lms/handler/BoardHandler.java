package com.eomcs.lms.handler;

import java.sql.Date;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.ArrayList;
import com.eomcs.util.Input;

public class BoardHandler {
  
  private ArrayList<Board> boardList = new ArrayList<>();
  private Input input;

  public BoardHandler(Input input) {
    this.input = input;
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
    Board[] boards = new Board[boardList.size()];
    boardList.toArray(boards);
    */
    for (Board board : boards) {
      System.out.printf("%s, %s, %s\n", board.getNo(), board.getContents(), board.getCreateDate(),
          board.getViewCount());
    }
  }

}
