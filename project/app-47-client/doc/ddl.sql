drop table project_board;
drop table project_member;
drop table project_time;

create table project_member(
  member_no int not null,
  id varchar(20) not null,
  password varchar(100) not null,
  building varchar(10) not null,
  unit varchar(10) not null,
  name varchar(20) not null,
  phonenumber varchar(40) not null,
  carnumber varchar(20) not null,
  VehicleOwnership varchar(20) null,
  residencestatus char(4) not null,
  entryTime datetime default now(),
  exitTime datetime default now()
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

alter table project_board
  add constraint primary key(board_no),
  modify column board_no int not null auto_increment;

create table project_time(
  carnumber varchar(20) not null,
  entryTime datetime,
  exitTime datetime
);

-- 게시판 작성자에 대해 외부키 설정
alter table myapp_board
  add constraint myapp_board_fk foreign key (writer) references myapp_member (member_no);
  

-- 게시판에 카테고리 컬럼 추가
alter table project_board
  add column category int not null;
  
-- 차량관리에 입차 출사 컬럼 추가
alter table project_member
  add column 
  
  
  