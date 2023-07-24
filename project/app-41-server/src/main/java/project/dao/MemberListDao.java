package project.dao;

import java.util.ArrayList;
import java.util.List;
import project.util.JsonDataHelper;
import project.vo.Member;

public class MemberListDao implements MemberDao {

  String filename;
  ArrayList<Member> list = new ArrayList<>();

  public MemberListDao(String filename) {
    this.filename = filename;
    JsonDataHelper.loadJson(filename, list, Member.class);
  }

  @Override
  public void insert(Member member) {
    member.setNo(Member.userId++);
    this.list.add(member);

    JsonDataHelper.saveJson(filename, list);
  }

  @Override
  public List<Member> list() {
    return this.list;
  }

  @Override
  public Member findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Member m = this.list.get(i);
      if (m.getNo() == no) {
        return m;
      }
    }
    return null;
  }

  @Override
  public int update(Member member) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == member.getNo()) {
        list.set(i, member);
        JsonDataHelper.saveJson(filename, list);
        return 1;
      }
    }
    return 0;
  }

  @Override
  public int delete(int no) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == no) {
        list.remove(i);
        JsonDataHelper.saveJson(filename, list);
        return 1;
      }
    }
    return 0;
  }

  @Override
  public void saveEntry(Member member) {
    // 기존 데이터에서 입력한 차량번호와 일치하는 차량을 찾습니다.
    boolean found = false;
    for (Member existingMember : list) {
      if (existingMember.getCarnumber().equals(member.getCarnumber())) {
        // 일치하는 차량을 찾았을 경우 해당 차량 정보를 업데이트합니다.
        existingMember.addEntryTime(System.currentTimeMillis());
        found = true;
        break;
      }
    }

    // 일치하는 차량을 찾지 못한 경우, 새로운 차량 정보를 리스트에 추가합니다.
    if (!found) {
      System.out.println("등록된 차량이 없습니다.");
    }

    // 변경된 리스트를 파일에 저장
    JsonDataHelper.saveJson(filename, list);
  }

  @Override
  public void saveExit(Member memeber) {
    boolean found = false;
    for (Member existingMember : list) {
      if (existingMember.getCarnumber().equals(memeber.getCarnumber())) {
        existingMember.addExitTime(System.currentTimeMillis());
        found =  true;
        break;
      }
    }
    if(!found) {
      System.out.println("등록된 차량이 없습니다.");
    }
    JsonDataHelper.saveJson(filename, list);
  }


}
