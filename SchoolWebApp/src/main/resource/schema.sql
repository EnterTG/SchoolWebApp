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

create table classtype (
  id varchar(300) primary key,
  description varchar(500) not null
);

create table classes (
  id int auto_increment primary key,
  name varchar(500) not null,
  semester numeric (15,3) not null,
  classtype varchar(500) not null,
  createyear int not null
);

create table classsubjects (
  id bigint auto_increment primary key,
  subjectid int not null,
  classid int not null,
  teacherid bigint not null,
);

create table students (
  id bigint auto_increment primary key,
  name varchar(500) not null,
  surname varchar(500) not null,
  bookid varchar(500) not null
);
create table classstudents (
  id bigint auto_increment primary key,
  studentid bigint not null,
  classid int not null
);

create table grades (
  id bigint auto_increment primary key,
  studentid bigint not null,
  classid int not null,
  subjectid int not null,
  grade varchar(500)
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

create table presencestudents (
	studentid bigint ,
	presenceid bigint ,
	presence boolean DEFAULT FALSE
);

create table presence (
	id bigint auto_increment primary key,
	classid int not null,
	subjectid int not null,
	date timestamp not null 
);
