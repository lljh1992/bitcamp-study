package project.dao;

import java.util.List;
import project.vo.Member;
import project.vo.ParkingTime;

public interface MemberDao {
  void insert(Member member);

  List<Member> findAll();

  void insertCar(Member member);

  Member findBy(String building);

  Member findByCar(String carnummber);

  List<ParkingTime> findinout();

  Member findByPhonenumberAndPassword(Member m);

  int update(Member member);

  int delete(String building);

  void saveEntry(Member member);

  void saveExit(Member member);


}
