package project.handler;

import java.util.List;
import project.dao.BoardDao;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.vo.Board;

public class BoardListListener implements ActionListener {

  BoardDao boardDao;

  public BoardListListener(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("----------------------------------------------------------------");
    System.out.println("번호 | 제목 | 작성자 | 조회수 | 등록일");
    System.out.println("----------------------------------------------------------------");

    List<Board> list = boardDao.list();

    for (Board board : list) {
      System.out.printf("%d, %s, %s, %d, %tY-%5$tm-%5$td\n", board.getNo(), board.getTitle(),
          board.getWriter(), board.getViewCount(), board.getCreatedDate());
    }
  }
}
