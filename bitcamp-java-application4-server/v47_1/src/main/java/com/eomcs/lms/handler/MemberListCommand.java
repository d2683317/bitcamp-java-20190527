package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Component;

@Component("/member/list")
public class MemberListCommand implements Command {
  private MemberDao memberDao;


  public MemberListCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {
    try {
      List<Member> members = memberDao.findAll();
      for (Member member : members) {
        out.printf("%s, %s, %s, %s, %s\n", member.getNo(), member.getName(), member.getEmail(),
            member.getTel(), member.getResisteredDate());
      }
    } catch (Exception e) {
      out.println("데이터 목록 조회 실패!!!");
      System.out.println(e.getMessage());
    }
  }

}
