<h1 align="center">
CRUD de Alunos
</h1>

Interface gráfica que permite criar, ler, atualizar e remover alunos de forma agradável ao usuário.

![print do programa](https://github.com/arthur-cristo-silva/University-CRUD/blob/main/src/com/studentscrud/prints/crudAlunos.png)

## Tecnologias
- Java 21
- Java Swing - Interface Gráfica
- MySQL - Banco de Dados

## Como executar
- Clonar repositório git:
```
git clone https://github.com/arthur-cristo-silva/University-CRUD.git
```
- Criar banco de dados e usário:
```
mysql> create database university;
mysql> create user 'user'@'%' identified by '';
mysql> grant all on university.* to 'user'@'%';
```
- Execute StudentsCrudUi.jar