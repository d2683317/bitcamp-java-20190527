package com.eomcs.lms.handler;

import com.eomcs.util.Input;

public class HiCommand implements Command {
  
  private Input input;
  
  public HiCommand(Input input) {
    this.input = input;
  }

  @Override
  public void execute() {
    System.out.println("안녕하세요, " +input.getStringValue("이름?") + "님!");
  }
}
