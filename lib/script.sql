create database if not exists university;
create user if not exists 'user'@'%' identified by '';
grant all on university.* to 'user'@'%';
use university;
create table if not exists students
(
    ra       bigint not null primary key auto_increment,
    name     varchar(100),
    age      varchar(3),
    course   varchar(100),
    schedule varchar(5),
    absences int(11)
) auto_increment = 11111111;
create table if not exists professors
(
    ra       bigint not null primary key auto_increment,
    name     varchar(100),
    age      varchar(3),
    email    varchar(100),
    workload varchar(2)
) auto_increment = 77777777;