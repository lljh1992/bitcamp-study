package project.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import project.vo.Member;

public class MySQLMemberDao implements MemberDao {

  SqlSessionFactory sqlSessionFactory;

  public MySQLMemberDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(Member member) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("project.dao.MemberDao.insert", member);
  }

  @Override
  public void insertCar(Member member) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("project.dao.MemberDao.insertCar", member);
  }

  @Override
  public List<Member> findAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectList("project.dao.MemberDao.findAll");
  }

  @Override
  public Member findBy(String building) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectOne("project.dao.MemberDao.findBy", building);
  }

  @Override
  public Member findByPhonenumberAndPassword(Member member) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectOne("project.dao.MemberDao.findByPhonenumberAndPassword", member);
  }

  @Override
  public int update(Member member) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("project.dao.MemberDao.update", member);
  }

  @Override
  public int delete(String building) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("project.dao.MemberDao.delete", building);
  }

  @Override
  public Member findByCar(String carnummber) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectOne("project.dao.MemberDao.findByCar", carnummber);
  }


}
