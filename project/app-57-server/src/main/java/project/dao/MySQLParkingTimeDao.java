package project.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import project.vo.ParkingTime;

public class MySQLParkingTimeDao implements ParkingTimeDao {

  SqlSessionFactory sqlSessionFactory;

  public MySQLParkingTimeDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void saveEntry(ParkingTime pt) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("project.dao.ParkingTimeDao.insertentryTime", pt);
  }

  @Override
  public void saveExit(ParkingTime pt) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("project.dao.ParkingTimeDao.insertexitTime", pt);
  }

  @Override
  public List<ParkingTime> findinout() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectList("project.dao.ParkingTimeDao.findinout");
  }

}
