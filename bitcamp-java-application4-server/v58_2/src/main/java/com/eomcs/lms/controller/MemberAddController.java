package com.eomcs.lms.controller;

import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
@Component("/member/add")
public class MemberAddController implements PageController {
  @Resource
  private MemberDao memberDao;

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (request.getMethod().equals("GET")) {
      return "/jsp/member/form.jsp";
    }
    
    String uploadDir = request.getServletContext().getRealPath("/upload/member");
    
    Member member = new Member();
    member.setName(request.getParameter("name"));
    member.setEmail(request.getParameter("email"));
    member.setPassword(request.getParameter("password"));
    member.setTel(request.getParameter("tel"));

    // 업로드 된 사진 파일 처리
    Part photoPart = request.getPart("photo");
    if (photoPart != null && photoPart.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      member.setPhoto(filename);
      photoPart.write(uploadDir + "/" + filename);
    }

    memberDao.insert(member);
    return "redirect:list";

  }

}
