create table department
(
    id bigint not null AUTO_INCREMENT,
    departmentName varchar(255) not null,
    primary key (id)
);

create table employee
(
    id bigint not null AUTO_INCREMENT,
    name varchar(255) not null,
    age int not null,
    departmentId bigint not null,
    primary key (id),
    foreign key (id) references department(id)
);