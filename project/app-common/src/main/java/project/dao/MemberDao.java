package project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import project.vo.Member;

public interface MemberDao {
  void insert(Member member);

  List<Member> findAll();

  void insertCar(Member member);

  Member findBy(String building);

  Member findByCar(String carnummber);

  Member findByPhonenumberAndPassword(@Param("phonenumber") String phonenumber, @Param("password") String password);

  int update(Member member);

  int delete(String building);

}
