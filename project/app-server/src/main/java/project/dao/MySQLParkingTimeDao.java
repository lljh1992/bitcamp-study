package project.dao;

import java.sql.Timestamp;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import project.util.Component;
import project.vo.ParkingTime;

@Component
public class MySQLParkingTimeDao implements ParkingTimeDao {

  SqlSessionFactory sqlSessionFactory;

  public MySQLParkingTimeDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void saveEntry(Timestamp Timestamp) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("project.dao.ParkingTimeDao.insertentryTime", Timestamp);
  }

  @Override
  public void saveExit(Timestamp Timestamp) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("project.dao.ParkingTimeDao.insertexitTime", Timestamp);
  }

  @Override
  public List<ParkingTime> findinout() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectList("project.dao.ParkingTimeDao.findinout");
  }

}
