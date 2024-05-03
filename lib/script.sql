create database if not exists university;
create user if not exists 'user'@'%' identified by '';
grant all on university.* to 'user'@'%';
use university;
create table if not exists students(
    ra bigint not null auto_increment primary key,
    name varchar(100),
    age varchar(3),
    course varchar(100),
    schedule varchar(5),
    absences int(11));