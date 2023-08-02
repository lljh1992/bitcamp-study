package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
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
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into project_member(id, password, building, unit, name, phonenumber, carnumber, VehicleOwnership, residencestatus)"
            + " value(?,sha1(?),?,?,?,?,?,?,?)")) {

      stmt.setString(1, member.getId());
      stmt.setString(2, member.getPassword());
      stmt.setString(3, member.getBuilding());
      stmt.setString(4, member.getUnit());
      stmt.setString(5, member.getName());
      stmt.setString(6, member.getPhonenumber());
      stmt.setString(7, member.getCarnumber());
      stmt.setString(8, member.getVehicleOwnership());
      stmt.setString(9, String.valueOf(member.getResidencestatus()));
      stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Member> list() {
    try (
        PreparedStatement stmt = con.prepareStatement(
            "select member_no, id, building, unit, name, phonenumber, carnumber, VehicleOwnership,"
                + " residencestatus from project_member order by member_no asc");

        ResultSet rs = stmt.executeQuery()) {

      List<Member> list = new ArrayList<>();

      while (rs.next()) {
        Member m = new Member();
        m.setNo(rs.getInt("member_no"));
        m.setId(rs.getString("id"));
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
    try (PreparedStatement stmt = con.prepareStatement("update project_member set"
        + " id=?, password=sha1(?)," + " building=?," + " unit=?," + " name=?," + " phonenumber=?,"
        + " carnumber=?," + " VehicleOwnership=?," + " residencestatus=?" + " where member_no=?")) {

      stmt.setString(1, member.getId());
      stmt.setString(2, member.getPassword());
      stmt.setString(3, member.getBuilding());
      stmt.setString(4, member.getUnit());
      stmt.setString(5, member.getName());
      stmt.setString(6, member.getPhonenumber());
      stmt.setString(7, member.getCarnumber());
      stmt.setString(8, member.getVehicleOwnership());
      stmt.setString(9, String.valueOf(member.getResidencestatus()));
      stmt.setInt(10, member.getNo());

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(int no) {
    try (PreparedStatement stmt =
        con.prepareStatement("delete from project_member where member_no=?")) {
      stmt.setInt(1, no);

      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean deleteNew(Member member) {
    try (PreparedStatement stmt = con.prepareStatement("DELETE FROM project_member WHERE id=?")) {
      stmt.setString(1, member.getId());
      int affectedRows = stmt.executeUpdate();

      // 삭제된 행의 수가 1 이상이면 삭제 성공
      return affectedRows > 0;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  public void saveEntry(Member member) {
    try (PreparedStatement pstmt =
        con.prepareStatement("insert into project_time (carnumber, entryTime) values (?, ?)")) {

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
    try (PreparedStatement pstmt =
        con.prepareStatement("insert into project_time (carnumber, exitTime) values (?, ?)")) {

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
  public boolean isExistingMember(String memberid) {
    try (PreparedStatement pstmt =
        con.prepareStatement("SELECT COUNT(*) AS count FROM project_member WHERE id=?")) {
      pstmt.setString(1, memberid);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          int count = rs.getInt("count");
          return count > 0;
        }
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return false;
  }

  @Override
  public Member findByNewId(String Id) {
    try (
        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM project_member WHERE id=?")) {
      pstmt.setString(1, Id);

      try (ResultSet rs = pstmt.executeQuery()) {
        if (rs.next()) {
          Member m = new Member();
          m.setNo(rs.getInt("member_no"));
          m.setId(rs.getString("id"));
          m.setPassword(rs.getString("password")); // 해시 처리된 비밀번호 가져오기
          m.setBuilding(rs.getString("building"));
          m.setUnit(rs.getString("unit"));
          m.setName(rs.getString("name"));
          m.setPhonenumber(rs.getString("phonenumber"));
          m.setCarnumber(rs.getString("carnumber"));
          m.setVehicleOwnership(rs.getString("VehicleOwnership"));
          m.setResidencestatus(rs.getString("residencestatus").charAt(0));

          return m;
        }
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    return null;
  }

  @Override
  public Member findBy(int no) {
    try (PreparedStatement stmt = con.prepareStatement(
        "select member_no, building, unit, name, phonenumber, carnumber, VehicleOwnership, residencestatus from project_member where member_no=?")) {

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
          return m;
        }
        return null;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
