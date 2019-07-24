package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.domain.Board;

public class BoardListCommand implements Command {

  private List<Board> list;

  public BoardListCommand(List<Board> list) {
    this.list = list;
  }

  @Override
  public void execute() {
    Board[] boards = list.toArray(new Board[] {});
    /*
     * Board[] boards = new Board[boardList.size()]; boardList.toArray(boards);
     */
    for (Board board : boards) {
      System.out.printf("%s, %s, %s\n", board.getNo(), board.getContents(), board.getCreateDate(),
          board.getViewCount());
    }
  }

}
