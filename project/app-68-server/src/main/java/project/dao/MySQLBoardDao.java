package project.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import project.vo.AttachedFile;
import project.vo.Board;

@Component
public class MySQLBoardDao implements BoardDao {

  SqlSessionFactory sqlSessionFactory;

  public MySQLBoardDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(Board board) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("project.dao.BoardDao.insert", board);
  }

  @Override
  public List<Board> findAll(int category) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectList("project.dao.BoardDao.findAll", category);
  }

  @Override
  public Board findBy(int category, int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);

    Map<String, Object> paramMap = new HashMap<>();
    paramMap.put("categoryNo", category);
    paramMap.put("boardNo", no);

    return sqlSession.selectOne("project.dao.BoardDao.findBy", paramMap);
  }

  @Override
  public int update(Board board) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("project.dao.BoardDao.update", board);
  }

  @Override
  public int updateCount(Board board) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("project.dao.BoardDao.updateCount", board);
  }

  @Override
  public int delete(Board board) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("project.dao.BoardDao.delete", board);
  }

  @Override
  public int insertFiles(Board board) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.insert("project.dao.BoardDao.insertFiles", board);
  }

  @Override
  public AttachedFile findFileBy(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectOne("project.dao.BoardDao.findFileBy", no);
  }

  @Override
  public int deleteFile(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("project.dao.BoardDao.deleteFile", no);
  }

  @Override
  public int deleteFiles(int boardNo) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("project.dao.BoardDao.deleteFiles", boardNo);
  }


}
