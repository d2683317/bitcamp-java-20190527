package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;
import com.eomcs.lms.util.Input;

public class MemberHandler {
  
  MemberList memberList = new MemberList();

  private Input input;

  public MemberHandler(Input input) {
    this.input = input;
  }

  public void addMember() {
    Member member = new Member();

    member.setNo(input.getIntValue("번호? "));
    member.setName(input.getStringValue("이름? "));
    member.setEmail(input.getStringValue("이메일? "));
    member.setPw(input.getStringValue("암호? "));
    member.setPhoto(input.getStringValue("사진? "));
    member.setTel(input.getStringValue("전화? "));
    member.setResisteredDate(input.getDateValue("가입일? "));

    memberList.add(member);
    System.out.println("저장하였습니다.");
  }

  public void listMember() {
    Member[] members = memberList.toArray();
    for (Member member : members) {

      System.out.printf("%s, %s, %s, %s, %s\n", member.getNo(), member.getName(), member.getEmail(),
          member.getTel(), member.getResisteredDate());

    }
  }

}
