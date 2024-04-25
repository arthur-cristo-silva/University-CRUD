create database university;

create table students(
    id bigint not null auto_increment primary_key,
    name varchar(100),
    ra varchar(11) unique,
    horario varchar(5),
    curso varchar(100),
    faltas int)

)