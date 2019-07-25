package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardUpdateCommand implements Command {

  private List<Board> list;
  private Input input;

  public BoardUpdateCommand(Input input, List<Board> list) {
    this.input = input;
    this.list = list;
  }
  
  @Override
  public void execute() {
    int no = input.getIntValue("번호?");

    Board board = null;
    for (int i = 0; i < list.size(); i++) {
      Board temp = list.get(i);
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

}
