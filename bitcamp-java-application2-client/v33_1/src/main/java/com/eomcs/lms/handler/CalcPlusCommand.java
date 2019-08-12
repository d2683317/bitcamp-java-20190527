package com.eomcs.lms.handler;

import com.eomcs.util.Input;

public class CalcPlusCommand implements Command {

  private Input input;
  
  public CalcPlusCommand(Input input) {
    this.input = input;
  }
  
  @Override
  public void execute() {
    int v1 = input.getIntValue("값1? ");
    int v2 = input.getIntValue("값2? ");
    System.out.printf("합계는 %d 입니다.", v1 + v2);
  }
}
