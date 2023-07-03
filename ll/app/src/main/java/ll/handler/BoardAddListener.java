package ll.handler;

import java.util.List;
import ll.util.BreadcrumbPrompt;
import ll.vo.Board;

public class BoardAddListener extends AbstractBoardListener {

  public BoardAddListener(List<Board> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Board board = new Board();
    board.setNo(Board.boardNo++);
    board.setTitle(prompt.inputString("제목? "));
    board.setContent(prompt.inputString("내용? "));
    board.setWriter(prompt.inputString("작성자? "));
    board.setPassword(prompt.inputString("암호? "));
    board.setCreatedDate(System.currentTimeMillis());
    this.list.add(board);
  }
}


