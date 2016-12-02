create table organization (id bigint not null auto_increment, createts datetime, latitude double precision not null, longitude double precision not null, name varchar(255) not null, updatets datetime, primary key (id));
create table route (id bigint not null auto_increment, createts datetime, latitude double precision, location varchar(50), longitude double precision, name varchar(50) not null, updatets datetime, vehicle_name varchar(50), vehicle_no varchar(50), driver_id bigint, organization_id bigint not null, primary key (id));
create table user (user_type varchar(31) not null, id bigint not null auto_increment, createts datetime, email_id varchar(50), mobile_no varchar(255) not null, name varchar(50) not null, password varchar(20) not null, role integer not null, updatets datetime, organization_id bigint, primary key (id));
alter table organization add constraint UK_8j5y8ipk73yx2joy9yr653c9t unique (name);
alter table user add constraint UK_dyyo2ju1rk7exhxr05xx47gca unique (mobile_no);
alter table route add constraint FK5xwg7f8rgs8syk743fyn2ylyj foreign key (driver_id) references user (id);
alter table route add constraint FKcqyl709tgov2g25bsvbg2k73h foreign key (organization_id) references organization (id);
alter table user add constraint FKi3ynrf4qjomj2hdjx7ssa3mlh foreign key (organization_id) references organization (id);
insert into user(user_type,mobile_no,name,password,role) values('S','9999999999','sadmin','password',0);