package com.eomcs.lms.controller;

import java.sql.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

@Component("/lesson/add")
public class LessonAddController {
  @Resource
  private LessonDao lessonDao;


  @RequestMapping
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (request.getMethod().equals("GET")) {
      return "/jsp/lesson/form.jsp";
    }
    Lesson lesson = new Lesson();

    // 사용자가 입력한 값을 Lesson 인스턴스의 각 변수에 저장한다.
    lesson.setTitle(request.getParameter("title"));
    lesson.setContents(request.getParameter("contents"));
    lesson.setStartDate(Date.valueOf(request.getParameter("startDate")));
    lesson.setEndDate(Date.valueOf(request.getParameter("endDate")));
    lesson.setTotalHours(Integer.parseInt(request.getParameter("totalHours")));
    lesson.setDayHours(Integer.parseInt(request.getParameter("dayHours")));
    lessonDao.insert(lesson);

    return "redirect:list";

  }

}
