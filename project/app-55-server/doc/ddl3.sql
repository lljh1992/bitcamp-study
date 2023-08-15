drop table project2_board;
drop table project2_building;
drop table project2_inout;
drop table project2_car;

create table project2_building(
  building_no varchar(30) not null,
  name varchar(20) not null,
  phonenumber varchar(40) not null,
  password varchar(100) not null,
  carnumber varchar(20) not null,
  created_date date default (current_date())
);

alter table project2_building
  add constraint primary key(building_no);
  
alter table project2_building
  add constraint project2_building_uk unique (phonenumber);

  
create table project2_board(
  board_no int not null,
  title varchar(255) not null,
  content text null,
  writer varchar(10) not null,
  password varchar(100) null,
  view_count int default 0,
  created_date datetime default now(),
  category int not null
);

alter table project2_board
  add constraint primary key(board_no),
  modify column board_no int not null auto_increment;
  
alter table project2_board
  add constraint project2_board_fk foreign key (writer) references project2_building (building_no);


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
  entryTimes datetime, 
  exitTimes datetime
);

alter table project2_inout
  add constraint primary key(inout_no),
  modify column inout_no int not null auto_increment;
  
  
alter table project2_inout
  add constraint project2_inout_fk foreign key (carnumber) references project2_car (car_no);
  