package project.handler;

import project.util.ActionListener;
import project.util.List;
import project.vo.NewMember;

public abstract class AbstractNewMemberListener implements ActionListener {

  protected List list;

  public AbstractNewMemberListener(List list) {
    this.list = list;
  }

  protected boolean isExistingMember(String newmemberid) {

    for (int i = 0; i < this.list.size(); i++) {
      NewMember nm = (NewMember) this.list.get(i);
      if (nm != null && nm.getNewid().equals(newmemberid)) {
        return true;
      }
    }
    return false;
  }
}
