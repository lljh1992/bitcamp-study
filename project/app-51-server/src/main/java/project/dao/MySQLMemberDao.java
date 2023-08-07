package project.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import project.util.DataSource;
import project.vo.Member;

public class MySQLMemberDao implements MemberDao {

  DataSource ds;

  public MySQLMemberDao(DataSource ds) {
    this.ds = ds;
  }

  @Override
  public void insert(Member member) {
    try (PreparedStatement stmt = ds.getConnection(false).prepareStatement(
        "insert into project_member(building, unit, name, phonenumber, password, carnumber, VehicleOwnership, residencestatus)"
            + " value(?,?,?,?,sha1(?),?,?,?)")) {

      stmt.setString(1, member.getBuilding());
      stmt.setString(2, member.getUnit());
      stmt.setString(3, member.getName());
      stmt.setString(4, member.getPhonenumber());
      stmt.setString(5, member.getPassword());
      stmt.setString(6, member.getCarnumber());
      stmt.setString(7, member.getVehicleOwnership());
      stmt.setString(8, String.valueOf(member.getResidencestatus()));
      stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Member> list() {
    try (
        PreparedStatement stmt = ds.getConnection(false).prepareStatement(
            "select member_no, building, unit, name, phonenumber, carnumber, VehicleOwnership,"
                + " residencestatus from project_member order by member_no asc");

        ResultSet rs = stmt.executeQuery()) {

      List<Member> list = new ArrayList<>();

      while (rs.next()) {
        Member m = new Member();
        m.setNo(rs.getInt("member_no"));
        m.setBuilding(rs.getString("building"));
        m.setUnit(rs.getString("unit"));
        m.setName(rs.getString("name"));
        m.setPhonenumber(rs.getNString("phonenumber"));
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
  public int update(Member member) {
    try (PreparedStatement stmt = ds.getConnection(false)
        .prepareStatement("update project_member set" + " building=?," + " unit=?," + " name=?,"
            + " phonenumber=?," + " password=sha1(?)," + " carnumber=?," + " VehicleOwnership=?,"
            + " residencestatus=?" + " where member_no=?")) {

      stmt.setString(1, member.getBuilding());
      stmt.setString(2, member.getUnit());
      stmt.setString(3, member.getName());
      stmt.setString(4, member.getPhonenumber());
      stmt.setString(5, member.getPassword());
      stmt.setString(6, member.getCarnumber());
      stmt.setString(7, member.getVehicleOwnership());
      stmt.setString(8, String.valueOf(member.getResidencestatus()));
      stmt.setInt(9, member.getNo());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(int no) {
    try (PreparedStatement stmt =
        ds.getConnection(false).prepareStatement("delete from project_member where member_no=?")) {
      stmt.setInt(1, no);

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void saveEntry(Member member) {
    try (PreparedStatement pstmt = ds.getConnection(false)
        .prepareStatement("insert into project_time (carnumber, entryTime) values (?, ?)")) {

      // 현재 시간을 Timestamp 객체로 생성
      Timestamp entryTime = new Timestamp(System.currentTimeMillis());

      // 입차 시간을 문자열로 변환하여 PreparedStatement에 설정
      pstmt.setString(1, member.getCarnumber());
      pstmt.setString(2, entryTime.toString());

      // 쿼리 실행
      pstmt.executeUpdate();

      // 멤버 객체에 입차 시간 추가
      member.addEntryTime(entryTime);

      System.out.println("차량번호: " + member.getCarnumber() + " 입차 기록이 저장되었습니다.");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }



  @Override
  public void saveExit(Member member) {
    try (PreparedStatement pstmt = ds.getConnection(false)
        .prepareStatement("insert into project_time (carnumber, exitTime) values (?, ?)")) {

      // 현재 시간을 Timestamp 객체로 생성
      Timestamp exitTime = new Timestamp(System.currentTimeMillis());

      // 입차 시간을 문자열로 변환하여 PreparedStatement에 설정
      pstmt.setString(1, member.getCarnumber());
      pstmt.setString(2, exitTime.toString());

      // 쿼리 실행
      pstmt.executeUpdate();

      // 멤버 객체에 입차 시간 추가
      member.addEntryTime(exitTime);

      System.out.println("차량번호: " + member.getCarnumber() + " 입차 기록이 저장되었습니다.");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public Member findBy(int no) {
    try (PreparedStatement stmt = ds.getConnection(false).prepareStatement(
        "select member_no, building, unit, name, phonenumber, carnumber, VehicleOwnership, residencestatus, created_date from project_member where member_no=?")) {

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Member m = new Member();
          m.setNo(rs.getInt("member_no"));
          m.setBuilding(rs.getString("building"));
          m.setUnit(rs.getString("unit"));
          m.setName(rs.getString("name"));
          m.setPhonenumber(rs.getNString("phonenumber"));
          m.setCarnumber(rs.getString("carnumber"));
          m.setVehicleOwnership(rs.getString("VehicleOwnership"));
          m.setResidencestatus(rs.getString("residencestatus").charAt(0));
          m.setCreatedDate(rs.getDate("created_date"));
          return m;
        }
        return null;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Member findByPhonenumberAndPassword(Member param) {
    try (PreparedStatement stmt = ds.getConnection(false).prepareStatement(
        "select member_no, building, unit, name, phonenumber, carnumber, VehicleOwnership, residencestatus, created_date from project_member where phonenumber=? and password=sha1(?)")) {

      stmt.setString(1, param.getPhonenumber());
      stmt.setString(2, param.getPassword());

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Member m = new Member();
          m.setNo(rs.getInt("member_no"));
          m.setBuilding(rs.getString("building"));
          m.setUnit(rs.getString("unit"));
          m.setName(rs.getString("name"));
          m.setPhonenumber(rs.getNString("phonenumber"));
          m.setCarnumber(rs.getString("carnumber"));
          m.setVehicleOwnership(rs.getString("VehicleOwnership"));
          m.setResidencestatus(rs.getString("residencestatus").charAt(0));
          m.setCreatedDate(rs.getDate("created_date"));
          return m;
        }
        return null;
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
