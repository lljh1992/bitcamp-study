package project.dao;

import java.util.ArrayList;
import java.util.List;
import project.util.JsonDataHelper;
import project.vo.NewMember;

public class NewMemberListDao implements NewMemberDao {

  String filename;
  ArrayList<NewMember> list = new ArrayList<>();

  public NewMemberListDao(String filename) {
    this.filename = filename;
    JsonDataHelper.loadJson(filename, list, NewMember.class);
  }

  @Override
  public void insert(NewMember newmember) {
    newmember.setNewno(newmember.newno++);
    this.list.add(newmember);

    JsonDataHelper.saveJson(filename, list);
  }

  @Override
  public List<NewMember> list() {
    return this.list;
  }

  @Override
  public boolean isExistingMember(String newmemberid) {

    for (int i = 0; i < this.list.size(); i++) {
      NewMember nm = this.list.get(i);
      if (nm != null && nm.getNewid().equals(newmemberid)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public int update(NewMember newmember) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNewno() == newmember.getNewno()) {
        list.set(i, newmember);
        JsonDataHelper.saveJson(filename, list);
        return 1;
      }
    }
    return 0;
  }

  @Override
  public int delete(int no) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNewno() == no) {
        list.remove(i);
        JsonDataHelper.saveJson(filename, list);
        return 1;
      }
    }
    return 0;
  }

  @Override
  public boolean deleteNew(NewMember newmember) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNewid().equals(newmember.getNewid())) {
        list.remove(i);
        JsonDataHelper.saveJson(filename, list);
        return true;
      }
    }
    return false;
  }


}
