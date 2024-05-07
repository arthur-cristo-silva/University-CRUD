<h1 align="center">University CRUD</h1>
<p align="center">
Este projeto consiste em um CRUD que gerencia professores e alunos em um banco de dados MySQL através de uma interface gráfica amigável ao usuário.
</p>

![crud de alunos](https://github.com/arthur-cristo-silva/University-CRUD/blob/main/lib/crudAlunos.gif)

## Como executar
- Configurar o MySQL:
```
create database if not exists university;
create user if not exists 'user'@'%' identified by '';
grant all on university.* to 'user'@'%';
use university;
create table if not exists students (
    ra       bigint not null primary key auto_increment,
    name     varchar(100),
    age      varchar(3),
    course   varchar(100),
    schedule varchar(5),
    absences int(11)) auto_increment = 100;
create table if not exists professors (
    ra       bigint not null primary key auto_increment,
    name     varchar(100),
    age      varchar(3),
    email    varchar(100),
    workload varchar(2)) auto_increment = 200;
```
- Baixar e executar University.jar