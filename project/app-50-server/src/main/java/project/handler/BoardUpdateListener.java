package project.handler;

import java.io.IOException;
import project.dao.BoardDao;
import project.util.ActionListener;
import project.util.BreadcrumbPrompt;
import project.vo.Board;
import project.vo.Member;

public class BoardUpdateListener implements ActionListener {

  BoardDao boardDao;

  public BoardUpdateListener(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    int boardNo = prompt.inputInt("번호: ");

    Board board = boardDao.findBy(boardNo);
    if (board == null) {
      prompt.println("해당 번호의 게시글이 없습니다!");
      return;
    }

    board.setTitle(prompt.inputString("제목(%s) > ", board.getTitle()));
    board.setContent(prompt.inputString("내용(%s) > ", board.getContent()));
    board.setWriter((Member) prompt.getAttribute("loginUser"));

    if (boardDao.update(board) == 0) {
      prompt.println("게시글 변경 권한이 없습니다.");
    } else {
      prompt.println("변경했습니다.");
    }
  }
}