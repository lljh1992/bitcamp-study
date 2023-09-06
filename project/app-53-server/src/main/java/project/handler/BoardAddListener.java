package project.controller;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import project.dao.BoardDao;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.vo.Board;
import project.vo.Member;

public class BoardAddListener implements ActionListener {

  int category;
  BoardDao boardDao;
  SqlSessionFactory sqlSessionFactory;

  public BoardAddListener(int category, BoardDao boardDao, SqlSessionFactory sqlSessionFactory) {
    this.category = category;
    this.boardDao = boardDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Board board = new Board();
    board.setTitle(prompt.inputString("제목: "));
    board.setContent(prompt.inputString("내용: "));
    board.setWriter((Member) prompt.getAttribute("loginUser"));
    board.setCategory(category);

    try {
      boardDao.insert(board);

      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}
