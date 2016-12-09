create table organization (id bigint not null auto_increment, createts datetime, latitude double not null, longitude double not null, name varchar(50) not null, updatets datetime, primary key (id));
create table route (id bigint not null auto_increment, createts datetime, drop_time varchar(10), latitude double, location varchar(50), longitude double, name varchar(50) not null, pickup_time varchar(10), updatets datetime, vehicle_name varchar(50), vehicle_no varchar(50), driver_id bigint, organization_id bigint not null, primary key (id));
create table route_point (id bigint not null auto_increment, createts datetime, drop_time varchar(10), landmark varchar(50), latitude double, location varchar(50), longitude double, pickup_time varchar(10), updatets datetime, route_id bigint, primary key (id));
create table subscribe (id bigint not null auto_increment, createts datetime, latitude double, location varchar(50), longitude double, notif_km integer, updatets datetime, route_id bigint not null, tracker_id bigint not null, primary key (id));
create table trip (id bigint not null auto_increment, distance_travelled bigint, endts datetime, last_lat double, last_long double, last_seq bigint, startts datetime, trip_status integer not null, route_id bigint, primary key (id));
create table trip_tracker (id bigint not null auto_increment, latitude double, longitude double, notifts datetime, trip_tracker_notif_status integer, subscribe_id bigint not null, trip_id bigint not null, primary key (id));
create table user (user_type varchar(31) not null, id bigint not null auto_increment, createts datetime, email_id varchar(50), mobile_no varchar(255) not null, name varchar(50) not null, password varchar(20) not null, role integer not null, updatets datetime, organization_id bigint, primary key (id));

alter table organization add constraint UK_8j5y8ipk73yx2joy9yr653c9t unique (name);
alter table subscribe add constraint UKffhd0o2gb3nhu1nte5rx3fuk5 unique (tracker_id, route_id);
alter table trip_tracker add constraint UKi9spnl4fgql1xt2w1d3ydmjkq unique (trip_id, subscribe_id);
alter table user add constraint UK_dyyo2ju1rk7exhxr05xx47gca unique (mobile_no);
alter table route add constraint FK5xwg7f8rgs8syk743fyn2ylyj foreign key (driver_id) references user(id);
alter table route add constraint FKcqyl709tgov2g25bsvbg2k73h foreign key (organization_id) references organization(id);
alter table route_point add constraint FK4t91b3gwkyqtd81m67mvjmg9f foreign key (route_id) references route(id);
alter table subscribe add constraint FKf6skdms0ttph5ktb09uugq10v foreign key (route_id) references route(id);
alter table subscribe add constraint FK36yf023lmdfpv59oy2i3irotj foreign key (tracker_id) references user(id);
alter table trip add constraint FKeva4adpyk6glllffnw5ypj20j foreign key (route_id) references route(id);
alter table trip_tracker add constraint FKdaqox4to22ryytoevfnrmcldc foreign key (subscribe_id) references subscribe(id);
alter table trip_tracker add constraint FKnqgrogdgq2a3ltdovw1cq42ui foreign key (trip_id) references trip(id);
alter table user add constraint FKi3ynrf4qjomj2hdjx7ssa3mlh foreign key (organization_id) references organization(id);

