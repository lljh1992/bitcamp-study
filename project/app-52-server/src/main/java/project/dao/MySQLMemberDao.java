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
    sqlSession.insert("project.dao.MemberDao2.insert", member);
  }

  @Override
  public List<Member> findAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectList("project.dao.MemberDao2.findAll");
  }

  @Override
  public Member findBy(String building) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectOne("project.dao.MemberDao2.findBy", building);
  }

  @Override
  public Member findByPhonenumberAndPassword(Member member) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectOne("project.dao.MemberDao2.findByPhonenumberAndPassword", member);
  }

  @Override
  public int update(Member member) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("project.dao.MemberDao2.update", member);
  }

  @Override
  public int delete(String building) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("project.dao.MemberDao2.delete", building);
  }

  @Override
  public void saveEntry(Member member) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("project.dao.MemberDao2.insertentryTime", member);
  }

  @Override
  public void saveExit(Member member) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("project.dao.MemberDao2.insertexitTime", member);
  }

}
