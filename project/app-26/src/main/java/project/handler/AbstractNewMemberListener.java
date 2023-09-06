package project.controller;

import java.util.List;
import project.util.ActionListener;
import project.vo.NewMember;

public abstract class AbstractNewMemberListener implements ActionListener {

  protected List<NewMember> list;

  public AbstractNewMemberListener(List<NewMember> list) {
    this.list = list;
  }

  protected boolean isExistingMember(String newmemberid) {

    for (int i = 0; i < this.list.size(); i++) {
      NewMember nm = this.list.get(i);
      if (nm != null && nm.getNewid().equals(newmemberid)) {
        return true;
      }
    }
    return false;
  }
}
