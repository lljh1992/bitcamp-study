package project.dao;

import java.util.List;
import project.vo.Member;

public interface MemberDao {
  void insert(Member member);

  List<Member> list();

  Member findBy(int no);

  int update(Member member);

  int delete(int no);

  boolean deleteNew(Member member);

  void saveEntry(Member member);

  void saveExit(Member member);

  boolean isExistingMember(String memberid);

  Member findByNewId(String newId);

}
