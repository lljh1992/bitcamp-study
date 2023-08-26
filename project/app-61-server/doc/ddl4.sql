drop table project2_board;
drop table project2_building;
drop table project2_inout;
drop table project2_car;
drop table project2_board_category;
drop table project2_board_file;

create table project2_building(
  building_no varchar(30) not null,
  name varchar(20) not null,
  phonenumber varchar(40) not null,
  password varchar(100) not null,
  carnumber varchar(20) not null,
  created_date date default (current_date()),
  photo varchar(255) null
);

alter table project2_building
  add constraint primary key(building_no);
  
alter table project2_building
  add constraint project2_building_uk unique (phonenumber);

create table project2_board(
  board_no integer not null,
  title varchar(255) not null,
  content MEDIUMTEXT null,
  view_count integer default 0,
  created_date datetime not null default now(),
  writer varchar(10) not null,
  category integer not null
);

alter table project2_board
  add constraint primary key(board_no),
  modify column board_no int not null auto_increment;
  
alter table project2_board
  add constraint project2_board_fk foreign key (writer) references project2_building (building_no);

create table project2_board_category(
  board_category_no integer not null,
  name varchar(50) not null
);

alter table project2_board_category
  add constraint primary key(board_category_no),
  modify column board_category_no int not null auto_increment;

alter table project2_board_category
  add constraint project2_board_category_uk unique (name);

create table project2_board_file (
  board_file_no integer not null,
  filepath varchar(255) not null,
  board_no integer not null
);

alter table project2_board_file
  add constraint primary key(board_file_no),
  modify column board_file_no integer not null auto_increment;

alter table project2_board
  add constraint project2_board_fk1 foreign key (category) references project2_board_category (board_category_no);

alter table project2_board_file
  add constraint project2_board_file_fk foreign key (board_no) references project2_board (board_no);

create table project2_car(
  car_no int not null,
  building_no varchar(10) not null,
  carnumber varchar(20) not null
);

alter table project2_car
  add constraint primary key(car_no),
  modify column car_no int not null auto_increment;
  
alter table project2_car
  add constraint project2_car_uk unique (carnumber);

alter table project2_car
  add constraint project2_car_fk foreign key (building_no) references project2_building (building_no);

create table project2_inout(
  inout_no int not null,
  carnumber varchar(20) not null,
  entryTimes TIMESTAMP, 
  exitTimes TIMESTAMP
);

alter table project2_inout
  add constraint primary key(inout_no),
  modify column inout_no int not null auto_increment;
  
  
alter table project2_inout
  add constraint project2_inout_fk foreign key (carnumber) references project2_car (car_no);
  