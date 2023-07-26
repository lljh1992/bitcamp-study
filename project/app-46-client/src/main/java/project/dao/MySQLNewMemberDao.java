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
          "insert into project_newmember(newid,newpassword,newname,newphonenumber) values('%s','%s','%s','%s')",
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
            "select newmember_no, newid, newname from project_newmember order by newname desc")) {

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
    try (Statement stmt = con.createStatement();
        ResultSet rs =
            stmt.executeQuery("SELECT COUNT(*) AS count FROM project_newmember WHERE newid = '"
                + newmemberid + "'")) {

      if (rs.next()) {
        int count = rs.getInt("count");
        return count > 0;
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return false;
  }


  @Override
  public int update(NewMember newmember) {

    try (Statement stmt = con.createStatement()) {
      return stmt.executeUpdate(String.format(
          "UPDATE project_newmember SET newid='%s'," + " newpassword='%s',"
              + " newphonenumber='%s' WHERE newmember_no='%s'",
          newmember.getNewid(), newmember.getNewpassword(), newmember.getNewphonenumber(),
          newmember.getNewno()));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(int no) {
    return 0;
  }

  @Override
  public boolean deleteNew(NewMember newmember) {
    try (Statement stmt = con.createStatement()) {
      // 회원 정보를 삭제하는 SQL 쿼리 실행
      String query =
          String.format("DELETE FROM project_newmember WHERE newid='%s'", newmember.getNewid());
      int affectedRows = stmt.executeUpdate(query);

      // 삭제된 행의 수가 1 이상이면 삭제 성공
      return affectedRows > 0;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public NewMember findByNewId(String newId) {
    try (Statement stmt = con.createStatement();
        ResultSet rs =
            stmt.executeQuery("SELECT * FROM project_newmember WHERE newid = '" + newId + "'")) {

      if (rs.next()) {
        NewMember newmember = new NewMember();
        newmember.setNewno(rs.getInt("newmember_no"));
        newmember.setNewid(rs.getString("newid"));
        newmember.setNewpassword(rs.getString("newpassword"));
        newmember.setNewname(rs.getString("newname"));
        newmember.setNewphonenumber(rs.getString("newphonenumber"));

        return newmember;
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return null;
  }



}
