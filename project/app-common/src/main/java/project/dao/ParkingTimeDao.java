package project.dao;

import java.sql.Timestamp;
import java.util.List;
import project.vo.ParkingTime;

public interface ParkingTimeDao {
  List<ParkingTime> findinout();

  void saveEntry(Timestamp Timestamp);

  void saveExit(Timestamp Timestamp);
}
