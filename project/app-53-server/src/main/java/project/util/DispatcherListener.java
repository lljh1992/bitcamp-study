package project.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import project.dao.BoardDao;
import project.dao.MemberDao;
import project.dao.MySQLBoardDao;
import project.dao.MySQLMemberDao;
import project.controller.BoardAddListener;
import project.controller.BoardDeleteListener;
import project.controller.BoardDetailListener;
import project.controller.BoardListListener;
import project.controller.BoardUpdateListener;
import project.controller.LoginListener;
import project.controller.MemberAddListener;
import project.controller.MemberDeleteListener;
import project.controller.MemberDetailListener;
import project.controller.MemberEntryListener;
import project.controller.MemberExitListener;
import project.controller.MemberListListener;
import project.controller.MemberUpdateListener;

public class DispatcherListener implements ActionListener {

  Map<String, Object> beanContainer = new HashMap<>();

  public DispatcherListener() throws Exception {

    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder()
        .build(Resources.getResourceAsStream("project/config/mybatis-config.xml")));
    beanContainer.put("sqlSessionFactory", sqlSessionFactory);

    MemberDao memberDao = new MySQLMemberDao(sqlSessionFactory);
    BoardDao boardDao = new MySQLBoardDao(sqlSessionFactory);
    beanContainer.put("memberDao", memberDao);
    beanContainer.put("boardDao", boardDao);

    beanContainer.put("login", new LoginListener(memberDao));

    beanContainer.put("member/add", new MemberAddListener(memberDao, sqlSessionFactory));
    beanContainer.put("member/list", new MemberListListener(memberDao));
    beanContainer.put("member/detail", new MemberDetailListener(memberDao));
    beanContainer.put("member/update", new MemberUpdateListener(memberDao, sqlSessionFactory));
    beanContainer.put("member/delete", new MemberDeleteListener(memberDao, sqlSessionFactory));
    beanContainer.put("member/entry", new MemberEntryListener(memberDao));
    beanContainer.put("member/exit", new MemberExitListener(memberDao));

    beanContainer.put("board/add", new BoardAddListener(1, boardDao, sqlSessionFactory));
    beanContainer.put("board/list", new BoardListListener(1, boardDao));
    beanContainer.put("board/detail", new BoardDetailListener(1, boardDao, sqlSessionFactory));
    beanContainer.put("board/update", new BoardUpdateListener(1, boardDao, sqlSessionFactory));
    beanContainer.put("board/delete", new BoardDeleteListener(1, boardDao, sqlSessionFactory));

    beanContainer.put("notice/add", new BoardAddListener(2, boardDao, sqlSessionFactory));
    beanContainer.put("notice/list", new BoardListListener(2, boardDao));
    beanContainer.put("notice/detail", new BoardDetailListener(2, boardDao, sqlSessionFactory));
    beanContainer.put("notice/update", new BoardUpdateListener(2, boardDao, sqlSessionFactory));
    beanContainer.put("notice/delete", new BoardDeleteListener(2, boardDao, sqlSessionFactory));
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    ActionListener listener = (ActionListener) beanContainer.get(prompt.getAttribute("menuPath"));
    if (listener == null) {
      throw new RuntimeException("해당 요청을 처리할 수 없습니다.");
    }
    listener.service(prompt);
  }

  public Object getBean(String name) {
    return beanContainer.get(name);
  }
}
