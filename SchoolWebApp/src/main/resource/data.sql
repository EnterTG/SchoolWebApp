-- users
insert into users (id, email, name, password, role) VALUES (1, 'admin', 'Steve', 'x61Ey612Kl2gpFL56FT9weDnpSo4AV8j8+qx2AuTHdRyY036xxzTTrw10Wq3+4qQyB+XURPWx1ONxp3Y3pB37A==', 'admin');
insert into users (id, email, name, password, role) VALUES (2, 'baker', 'Teacher', 'x61Ey612Kl2gpFL56FT9weDnpSo4AV8j8+qx2AuTHdRyY036xxzTTrw10Wq3+4qQyB+XURPWx1ONxp3Y3pB37A==', 'teacher');

-- roles
insert into roles (id, description) VALUES ('admin', 'Administrator');
insert into roles (id, description) VALUES ('teacher', 'Nauczyciel');

insert into classtype (id, description) VALUES ('dzienne', 'Dzienne');
insert into classtype (id, description) VALUES ('zaoczne', 'Zaoczne');
insert into classtype (id, description) VALUES ('weekendowe', 'Weekendowe');


insert into students (id, name,surname,bookid) VALUES (1, 'Name1','Surname1',1);
insert into students (id, name,surname,bookid) VALUES (2, 'Name2','Surname2',2);
insert into students (id, name,surname,bookid) VALUES (3, 'Name3','Surname3',3);
insert into students (id, name,surname,bookid) VALUES (4, 'Name4','Surname4',4);
insert into students (id, name,surname,bookid) VALUES (5, 'Name5','Surname5',5);
insert into students (id, name,surname,bookid) VALUES (6, 'Name6','Surname6',6);

insert into classes (id, name,semester,classtype,createyear) VALUES (1, 'Class1',1,'dzienne',1995);

insert into classstudents (id, studentid,classid) VALUES (1,1,1);
insert into classstudents (id, studentid,classid) VALUES (2,2,1);
insert into classstudents (id, studentid,classid) VALUES (3,5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);

insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);
insert into classstudents ( studentid,classid) VALUES (5,1);

insert into subjects (id, name) VALUES (1,'Test subject');

insert into classsubjects (id, subjectid,classid,teacherid) VALUES (1,1,1,2);