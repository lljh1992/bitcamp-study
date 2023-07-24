package project.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import project.net.RequestEntity;
import project.net.ResponseEntity;
import project.vo.NewMember;

public class NewMemberNetworkDao implements NewMemberDao {

  String dataName;
  DataInputStream in;
  DataOutputStream out;

  public NewMemberNetworkDao(String dataName, DataInputStream in, DataOutputStream out) {
    this.dataName = dataName;
    this.in = in;
    this.out = out;
  }

  @Override
  public void insert(NewMember newmember) {
    try {
      out.writeUTF(new RequestEntity().command(dataName + "/insert").data(newmember).toJson());

      ResponseEntity response = ResponseEntity.fromJson(in.readUTF());
      if (response.getStatus().equals(ResponseEntity.ERROR)) {
        throw new RuntimeException(response.getResult());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<NewMember> list() {
    try {
      out.writeUTF(new RequestEntity().command(dataName + "/list").toJson());

      ResponseEntity response = ResponseEntity.fromJson(in.readUTF());
      if (response.getStatus().equals(ResponseEntity.FAILURE)) {
        throw new RuntimeException(response.getResult());
      }

      return response.getList(NewMember.class);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(NewMember newmember) {
    try {
      // 서버에 요청을 보낸다.
      out.writeUTF(new RequestEntity().command(dataName + "/update").data(newmember).toJson());

      // 서버에서 보낸 응답을 받는다.
      ResponseEntity response = ResponseEntity.fromJson(in.readUTF());

      if (response.getStatus().equals(ResponseEntity.ERROR)) {
        throw new RuntimeException(response.getResult());
      } else if (response.getStatus().equals(ResponseEntity.FAILURE)) {
        return 0;
      }

      return response.getObject(Integer.class);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(int no) {
    try {
      out.writeUTF(new RequestEntity().command(dataName + "/delete").data(no).toJson());

      ResponseEntity response = ResponseEntity.fromJson(in.readUTF());

      if (response.getStatus().equals(ResponseEntity.ERROR)) {
        throw new RuntimeException(response.getResult());
      } else if (response.getStatus().equals(ResponseEntity.FAILURE)) {
        return 0;
      }

      return response.getObject(Integer.class);

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean isExistingMember(String newmemberid) {
    try {
      out.writeUTF(new RequestEntity().command("isExistingMember").data(newmemberid).toJson());

      ResponseEntity response = ResponseEntity.fromJson(in.readUTF());
      if (response.getStatus().equals(ResponseEntity.SUCCESS)) {
        return response.getResult() != null;
      } else {
        throw new RuntimeException(response.getResult());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean deleteNew(NewMember newmember) {
    try {
      out.writeUTF(new RequestEntity().command("deleteNew").data(newmember).toJson());

      ResponseEntity response = ResponseEntity.fromJson(in.readUTF());
      if (response.getStatus().equals(ResponseEntity.SUCCESS)) {
        return response.getResult() != null;
      } else {
        throw new RuntimeException(response.getResult());
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
