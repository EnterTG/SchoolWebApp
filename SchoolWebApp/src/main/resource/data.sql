-- users
insert into users (id, email, name, password, role) VALUES (1, 'admin@holon-platform.com', 'Steve', 'x61Ey612Kl2gpFL56FT9weDnpSo4AV8j8+qx2AuTHdRyY036xxzTTrw10Wq3+4qQyB+XURPWx1ONxp3Y3pB37A==', 'admin');
insert into users (id, email, name, password, role) VALUES (2, 'baker@holon-platform.com', 'Teacher', 'rqOqT04K8MaDM8l29pAqQr1LeP66H4BvNdsx+NVQsOvg3LX+MAcQl92n3MADWI2i0YhMIMQqeKK3bM8WIkWdnA==', 'teacher');

-- roles
insert into roles (id, description) VALUES ('admin', 'Administrator');
insert into roles (id, description) VALUES ('teacher', 'Teacher');

insert into classtype (id, description) VALUES ('dzienne', 'Dzienne');
insert into classtype (id, description) VALUES ('zaoczne', 'Zaoczne');
insert into classtype (id, description) VALUES ('weekendowe', 'Weekendowe');