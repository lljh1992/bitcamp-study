-- project2_building 테이블 예제 데이터
insert into project2_building(building_no, name, phonenumber, password, carnumber)
  values('108-1704', '홍길동', '010-1111-1111', sha1('1111'), '11가1111');
insert into project2_building(building_no, name, phonenumber, password, carnumber)
  values('101-1102', '임꺽정', '010-2222-2222', sha1('2222'), '22가2222');  
insert into project2_building(building_no, name, phonenumber, password, carnumber)
  values('104-1001', '이순신', '010-3333-3333', sha1('3333'), '33가3333');
insert into project2_building(building_no, name, phonenumber, password, carnumber)
  values('000-0000', '관리자', '010-0000-0000', sha1('0000'), '00가0000');
  
-- project2_board_category 테이블 예제 데이터
insert into project2_board_category(board_category_no, name) values(1, '민원사항');
insert into project2_board_category(board_category_no, name) values(2, '공지사항');

-- project2_board 테이블 예제 데이터
insert into project2_board(board_no, title, content, writer, category)
  values(1, '민원사항1', '내용1', '108-1704', 1);
insert into project2_board(board_no, title, content, writer, category)
  values(2, '민원사항2', '내용2', '101-1102', 1);
insert into project2_board(board_no, title, content, writer, category)
  values(3, '민원사항3', '내용3', '104-1001', 1);
insert into project2_board(board_no, title, content, writer, category)
  values(11, '공지사항1', '내용1', '000-0000', 2);
insert into project2_board(board_no, title, content, writer, category)
  values(12, '공지사항2', '내용2', '000-0000', 2);
insert into project2_board(board_no, title, content, writer, category)
  values(13, '공지사항3', '내용3', '000-0000', 2);
  
-- project2_car 테이블 예제 데이터
insert into project2_car(car_no, building_no, carnumber)
  values(1, '108-1704', '11가1111');
insert into project2_car(car_no, building_no, carnumber)
  values(2, '101-1102', '22가2222');
insert into project2_car(car_no, building_no, carnumber)
  values(3, '104-1001', '33가3333');
  
-- project2_inout 테이블 예제 데이터
insert into project2_inout(inout_no, carnumber, entryTimes, exitTimes)
  values(1, '11가1111', '2023-07-26 16:46:49', '2023-07-26 18:46:49');
insert into project2_inout(inout_no, carnumber, entryTimes, exitTimes)
  values(2, '22가2222', '2023-07-30 16:46:49', '2023-07-31 18:46:49');
insert into project2_inout(inout_no, carnumber, entryTimes, exitTimes)
  values(3, '33가3333', '2023-08-01 16:46:49', '2023-08-01 18:46:49');