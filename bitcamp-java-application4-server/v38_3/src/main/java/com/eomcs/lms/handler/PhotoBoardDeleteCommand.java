package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.sql.SQLException;
import com.eomcs.lms.App;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.util.Input;

public class PhotoBoardDeleteCommand implements Command {

  private PhotoBoardDao photoBoardDao;
  private PhotoFileDao photoFileDao;

  public PhotoBoardDeleteCommand(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {
    try {
      App.con.setAutoCommit(false);
      
      int no = Input.getIntValue(in, out, "번호?");

      if (photoBoardDao.findBy(no) == null) {
        out.println("해당 데이터가 없습니다.");
        return;
      }

      // 먼저 게시물에 첨부파일을 삭제한다.
      photoFileDao.deleteAll(no);

      // 게시물을 삭제한다.
      photoBoardDao.delete(no);
      
      App.con.commit();
      
      out.println("데이터를 삭제했습니다.");

    } catch (Exception e) {
      try {
        App.con.rollback();
      } catch (SQLException e1) {
      }
      out.println("데이터 삭제 실패!!");
      System.out.println(e.getMessage());

    } finally {
      try {
        App.con.setAutoCommit(true);
      } catch (SQLException e) {
      }
    }
  }

}
