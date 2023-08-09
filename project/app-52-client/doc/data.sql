
-- project_memnber 테이블 예제 데이터
insert into project_member(member_no, id, password, building, unit, name, phonenumber, carnumber, VehicleOwnership, residencestatus)
  value(1, '1111', sha1('1111'), '108', '1704', '홍길동', '010-1111-1111', '11가1111', '1', 'Y');
insert into project_member(member_no, id, password, building, unit, name, phonenumber, carnumber, VehicleOwnership, residencestatus)
  value(2, '2222', sha1('2222'), '101', '1102', '임꺽정', '010-2222-2222', '22가2222', '2', 'N');
insert into project_member(member_no, id, password, building, unit, name, phonenumber, carnumber, VehicleOwnership, residencestatus)
  value(3, '3333', sha1('3333'), '104', '1001', '이순신', '010-3333-3333', '33가3333', '2', 'Y');
  
-- project_board 테이블 예제 데이터
insert into project_board(board_no, title, content, writer, password, category)
  values(1, '민원사항1', '내용1', '홍길동', '1111', 1);
insert into project_board(board_no, title, content, writer, password, category)
  values(2, '민원사항3', '내용2', '임꺽정', '1111', 1);
insert into project_board(board_no, title, content, writer, password, category)
  values(3, '민원사항2', '내용3', '이순신', '1111', 1);

-- project_notice 테이블 예제 데이터
insert into project_board(board_no, title, content, writer, password, category)
  values(11, '공지사항1', '내용1', '관리자1', '1111', 2);
insert into project_board(board_no, title, content, writer, password, category)
  values(12, '공지사항2', '내용2', '관리자1', '1111', 2);
insert into project_board(board_no, title, content, writer, password, category)
  values(13, '공지사항3', '내용3', '관리자1', '1111', 2);
  
-- project_time 테이블 예제 데이터
insert into project_time(carnumber, entryTime, exitTime)
  values ('11가1111', '2023-07-26 16:46:49', '2023-07-26 18:46:49');