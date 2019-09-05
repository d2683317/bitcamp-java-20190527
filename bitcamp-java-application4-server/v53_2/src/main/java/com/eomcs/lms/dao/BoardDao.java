package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.Board;

// 게시물 관리 DAO의 사용 규칙을 정의한다.
public interface BoardDao {
  int insert(Board board) throws Exception;
  List<Board> findAll() throws Exception;
  Board findBy(int no) throws Exception;
  int update(Board board) throws Exception;
  int delete(int no) throws Exception;
  int increaseViewCount(int no) throws Exception;
}
