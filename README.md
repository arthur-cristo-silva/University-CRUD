<h1 align="center">University CRUD</h1>

<div align="center">
<p align="center">Este projeto consiste em um CRUD que gerencia professores e alunos em um banco de dados MySQL através de uma interface gráfica amigável ao usuário.</p>
<img src="https://github.com/arthur-cristo-silva/University-CRUD/blob/main/lib/crudAlunos.gif">
</div>

<h2 align=center>
Como Executar</h2>

#### Criar Banco de Dados e Usuário MySQL:
```
create database if not exists university;
create user if not exists 'user'@'%' identified by '';
grant all on university.* to 'user'@'%';
```
#### Baixar e executar [University.jar](https://github.com/arthur-cristo-silva/university-crud/blob/main/University.jar)