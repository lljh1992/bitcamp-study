drop table project_board;
drop table project_member;
drop table project_newmember;

create table project_newmember(
  newmember_no int not null,
  newid varchar(20) not null,
  newpassword varchar(100) null,
  newname varchar(20) not null,
  newphonenumber varchar(20) not null
);

alter table project_newmember
  add constraint primary key(newmember_no),
  modify column newmember_no int not null auto_increment;

create table project_member(
  member_no int not null,
  building varchar(4) not null,
  unit varchar(4) not null,
  name varchar(20) not null,
  phonenumber varchar(20) not null,
  carnumber varchar(15) not null,
  VehiceOwnership varchar(2) default null,
  residencestatus char(1) not null,
  entryTimes datetime default now(),
  exitTimes datetime default now()
);

alter table project_member
  add constraint primary key(member_no),
  modify column member_no int not null auto_increment;

create table project_board(
  board_no int not null,
  title varchar(255) not null,
  content text null,
  writer varchar(20) not null,
  password varchar(100) null,
  view_count int default 0,
  created_date datetime default now(),
  category int not null
);

-- 게시판에 카테고리 컬럼 추가
alter table project_board
  add column category int not null;
  
-- 차량관리에 입차 출사 컬럼 추가
alter table project_member
  add column 