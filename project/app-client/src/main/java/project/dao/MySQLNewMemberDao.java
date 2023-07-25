package project.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import project.vo.NewMember;

public class MySQLNewMemberDao implements NewMemberDao {

  Connection con;

  public MySQLNewMemberDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(NewMember newmember) {
    try (Statement stmt = con.createStatement()) {

      stmt.executeUpdate(String.format(
          "insert into myapp_member(newid,newpassword,newname,newphonenumber) values('%s','%s','%s','%s')",
          newmember.getNewid(), newmember.getNewpassword(), newmember.getNewname(),
          newmember.getNewphonenumber()));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<NewMember> list() {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select newmember_no, newid, newname from project_newmember order by newname asc")) {

      List<NewMember> list = new ArrayList<>();

      while (rs.next()) {
        NewMember nm = new NewMember();
        nm.setNewno(rs.getInt("newmember_no"));
        nm.setNewid(rs.getString("newid"));
        nm.setNewname(rs.getString("newname"));

        list.add(nm);
      }

      return list;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean isExistingMember(String newmemberid) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public int update(NewMember newmember) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int delete(int no) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public boolean deleteNew(NewMember newmember) {
    // TODO Auto-generated method stub
    return false;
  }
}
