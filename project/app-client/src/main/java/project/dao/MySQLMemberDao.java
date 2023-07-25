package project.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import project.vo.Member;

public class MySQLMemberDao implements MemberDao {

  Connection con;

  public MySQLMemberDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Member member) {
    try (Statement stmt = con.createStatement()) {

      stmt.executeUpdate(String.format(
          "insert into project_member(building, unit, name, phonenumber, carnumber, VehiceOwnership, residencestatus)"
              + " value('$s','%s','%s','%s','%s','%s','$c')",
          member.getBuilding(), member.getUnit(), member.getName(), member.getPhonenumber(),
          member.getCarnumber(), member.getVehicleOwnership(), member.getResidencestatus()));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Member> list() {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select member_no, builder, unit, name, phonenumber, carnumber, VehicleOwnership, residencestatus from myapp_member order by name asc")) {

      List<Member> list = new ArrayList<>();

      while (rs.next()) {
        Member m = new Member();
        m.setNo(rs.getInt("member_no"));
        m.setBuilding(rs.getString("builder"));
        m.setUnit(rs.getString("unit"));
        m.setName(rs.getNString("phonenumber"));
        m.setCarnumber(rs.getString("carnumber"));
        m.setVehicleOwnership(rs.getString("VehicleOwnership"));
        m.setResidencestatus(rs.getString("residencestatus").charAt(0));

        list.add(m);
      }
      return list;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Member findBy(int no) {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select member_no, builder, unit, name, phonenumber, carnumber, VehicleOwnership, residencestatus from myapp_member order by name asc")) {

      List<Member> list = new ArrayList<>();

      while (rs.next()) {
        Member m = new Member();
        m.setNo(rs.getInt("member_no"));
        m.setBuilding(rs.getString("builder"));
        m.setUnit(rs.getString("unit"));
        m.setName(rs.getNString("phonenumber"));
        m.setCarnumber(rs.getString("carnumber"));
        m.setVehicleOwnership(rs.getString("VehicleOwnership"));
        m.setResidencestatus(rs.getString("residencestatus").charAt(0));
        return m;
      }
      return null;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Member member) {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate(String.format(
          "update project_member set" + " building='%s'," + " unit='%s'," + " name='%s',"
              + " phonenumber='%s'," + " carnumber='%s'," + " VehicleOwnership='%s',"
              + " residencestatus='%c'" + " where member_no=%d",
          member.getBuilding(), member.getUnit(), member.getName(), member.getPhonenumber(),
          member.getCarnumber(), member.getVehicleOwnership(), member.getResidencestatus(),
          member.getNo()));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(int no) {
    try (Statement stmt = con.createStatement()) {
      return stmt.executeUpdate(String.format("delete from project_member where member_no=%d", no));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void saveEntry(Member member) {
    try (Statement stmt = con.createStatement()) {

      stmt.executeUpdate(String.format("insert into project_member(entryTime)" + " value('$s')",
          member.getEntryTimes()));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public void saveExit(Member member) {
    try (Statement stmt = con.createStatement()) {

      stmt.executeUpdate(String.format("insert into project_member(exitTime)" + " value('$s')",
          member.getExitTimes()));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

}
