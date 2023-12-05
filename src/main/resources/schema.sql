create table departments
(
    id serial primary key,
    departmentName varchar(255) not null
);

create table employees
(
    id serial primary key,
    name varchar(255) not null,
    age int not null,
    departmentId bigint not null,
    foreign key (departmentId) references departments(id)
);