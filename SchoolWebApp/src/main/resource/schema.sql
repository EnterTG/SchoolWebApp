create table users (
  id bigint auto_increment primary key,
  email varchar(500) not null,
  name varchar(300) not null,
  password varchar(500) not null,
  role varchar(300) not null
);

create table roles (
  id varchar(300) primary key,
  description varchar(500) not null
);

create table classes (
  id int auto_increment primary key,
  name varchar(500) not null,
  semester numeric (15,3) not null,
  classtype varchar(500) not null,
  createyear date not null
);

create table classstudents (
  id bigint auto_increment primary key,
  studentid bigint not null,
  classid bigint not null
);

create table grades (
  id bigint auto_increment primary key,
  studentid bigint not null,
  classid int not null,
  subjectid int not null
  
);

create table subjects (
  id int auto_increment primary key,
  name varchar(500) not null
);

create table teachers (
	id bigint auto_increment primary key,
	userid bigint not null,
	classid int not null,
	subjectid int not null
);
