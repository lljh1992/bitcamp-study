package project.dao;

import java.util.List;
import project.vo.ParkingTime;

public interface ParkingTimeDao {
  List<ParkingTime> findinout();

  void saveEntry(ParkingTime pt);

  void saveExit(ParkingTime pt);
}
