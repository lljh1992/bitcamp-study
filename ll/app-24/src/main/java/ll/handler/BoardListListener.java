package ll.handler;

import ll.util.BreadcrumbPrompt;
import ll.util.List;
import ll.vo.Board;

public class BoardListListener extends AbstractBoardListener {

  public BoardListListener(List<Board> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------");
    System.out.println("번호, 제목, 작성자, 조회수, 등록일");
    System.out.println("---------------------------------------");

    for (int i = 0; i < this.list.size(); i++) {
      Board board = this.list.get(i);
      System.out.printf("%d, %s, %s, %d, %tY-%5$tm-%5$td\n", board.getNo(), board.getTitle(),
          board.getWriter(), board.getViewCount(), board.getCreatedDate());
    }
  }
}


