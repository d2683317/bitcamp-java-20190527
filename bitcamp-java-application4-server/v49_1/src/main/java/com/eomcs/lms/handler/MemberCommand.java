package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Component;
import com.eomcs.util.Input;
import com.eomcs.util.RequestMapping;

@Component
public class MemberCommand {

  private MemberDao memberDao;

  public MemberCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @RequestMapping("/member/add")
  public void add(BufferedReader in, PrintStream out) {

    try {
      Member member = new Member();
      //member.setNo(input.getIntValue("번호? "));
      member.setName(Input.getStringValue(in, out, "이름? "));
      member.setEmail(Input.getStringValue(in, out, "이메일? "));
      member.setPassword(Input.getStringValue(in, out, "암호? "));
      member.setPhoto(Input.getStringValue(in, out, "사진? "));
      member.setTel(Input.getStringValue(in, out, "전화? "));

      memberDao.insert(member);
      out.println("저장하였습니다.");
    } catch (Exception e) {
      out.println("데이터 저장 실패!!");
      System.out.println(e.getMessage());
    }
  }
  
  @RequestMapping("/member/delete")
  public void delete(BufferedReader in, PrintStream out) {
    try {
      int no = Input.getIntValue(in, out, "번호? ");

      if (memberDao.delete(no) > 0) {
        out.println("데이터를 삭제하였습니다.");
      } else {
        out.println("해당 데이터가 없습니다.");
      }
    } catch (Exception e) {
      out.println("데이터 삭제 실패");
      System.out.println(e.getMessage());
    }
  }
  
  @RequestMapping("/member/detail")
  public void detail(BufferedReader in, PrintStream out) {
    try {
      int no = Input.getIntValue(in, out, "번호? ");

      Member member = memberDao.findBy(no);
      if (member == null) {
        out.println("해당 번호의 데이터가 없습니다!");
        return;
      }
      out.printf("이름: %s\n", member.getName());
      out.printf("이메일: %s\n", member.getEmail());
      out.printf("사진: %s\n", member.getPhoto());
      out.printf("전화: %s\n", member.getTel());
      out.printf("가입일: %s\n", member.getResisteredDate());

    } catch (Exception e) {
      out.println("데이터 조회실패!!");
      System.out.println(e.getMessage());
    }
  }
  
  @RequestMapping("/member/list")
  public void list(BufferedReader in, PrintStream out) {
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
  
  @RequestMapping("/member/search")
  public void search(BufferedReader in, PrintStream out) {
    try {
      String keyword = Input.getStringValue(in, out, "검색어? ");
      
      List<Member> members = memberDao.findByKeyword(keyword);
      for (Member member : members) {
        out.printf("%s, %s, %s, %s, %s\n", member.getNo(), member.getName(), member.getEmail(),
            member.getTel(), member.getResisteredDate());
      }
    } catch (Exception e) {
      out.println("데이터 목록 조회 실패!!!");
      System.out.println(e.getMessage());
    }
  }
  
  @RequestMapping("/member/update")
  public void update(BufferedReader in, PrintStream out) {

    try {
      int no = Input.getIntValue(in, out, "번호? ");

      Member member = memberDao.findBy(no);

      if (member == null) {
        out.println("해당 번호의 데이터가 없습니다!");
        return;
      }
      
      // 사용자로부터 변경할 값을 입력 받는다.
      Member data = new Member();
      data.setNo(no);
      
      String str = Input.getStringValue(in, out, "이름(" + member.getName() + ")? ");
      if (str.length() > 0) {
        data.setName(str);
      }
      str = Input.getStringValue(in, out, "이메일(" + member.getEmail() + ")? ");
      if (str.length() > 0) {
        data.setEmail(str);
      }

      str = Input.getStringValue(in, out, "암호? ");
      if (str.length() > 0) {
        data.setPassword(str);
      }
      str = Input.getStringValue(in, out, "사진(" + member.getPhoto() + ")? ");
      if (str.length() > 0) {
        data.setPhoto(str);
      }
      str = Input.getStringValue(in, out, "전화(" + member.getTel() + ")? ");
      if (str.length() > 0) {
        data.setTel(str);
      }

      memberDao.update(data);
      
      out.println("데이터를 변경했습니다.");

    } catch (Exception e) {
      out.println("데이터 변경 실패!");
      System.out.println(e.getMessage());
    }
  }

}
