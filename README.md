<h1 align="center">
CRUD de Alunos
</h1>

Interface gráfica que permite criar, ler, atualizar e remover alunos de forma agradável ao usuário.

![crud de alunos](https://github.com/arthur-cristo-silva/University-CRUD/blob/main/lib/crudAlunos.png)

## Tecnologias
- Java 21
- Java Swing
- MySQL Connector

## Como executar
- Clonar repositório git:
```
git clone https://github.com/arthur-cristo-silva/University-CRUD.git
```
- Configurar o MySQL:
```
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
```
- Execute StudentsCrudUi.jar