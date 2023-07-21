package project.dao;

import java.util.List;
import project.vo.NewMember;

public interface NewMemberDao {
  void insert(NewMember newmember);

  List<NewMember> list();

  boolean isExistingMember(String newmemberid);

  int update(NewMember newmember);

  int delete(int no);

  boolean deleteNew(NewMember newmember);


}
