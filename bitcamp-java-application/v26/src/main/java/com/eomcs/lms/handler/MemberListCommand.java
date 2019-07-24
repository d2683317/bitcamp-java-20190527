package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.domain.Member;

public class MemberListCommand implements Command {

  private List<Member> list;


  public MemberListCommand(List<Member> list) {
    this.list = list;
  }

  @Override
  public void execute() {
    Member[] members = list.toArray(new Member[] {});
    for (Member member : members) {
      System.out.printf("%s, %s, %s, %s, %s\n", member.getNo(), member.getName(), member.getEmail(),
          member.getTel(), member.getResisteredDate());

    }
  }

}
