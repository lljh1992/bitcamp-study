package project.dao;

import java.util.List;
import project.vo.Member;

public interface MemberDao {
  void insert(Member member);

  List<Member> findAll();

  Member findBy(String building);

  Member findByPhonenumberAndPassword(Member m);

  int update(Member member);

  int delete(String building);

  void saveEntry(Member member);

  void saveExit(Member member);


}
