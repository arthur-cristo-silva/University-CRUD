# University CRUD

Este projeto consiste em um CRUD que gerencia professores e alunos em um banco de dados MySQL através de uma interface gráfica amigável ao usuário.

<img src="https://github.com/arthur-cristo-silva/expounifacs-jcrud/blob/main/src/main/resources/professors.gif" alt="Gif demonstrando o funcionamento do gerenciamento de professores. (Versão Antiga)">

## Como Executar
- Crie o Banco de Dados e Usuário MySQL:
```
create database if not exists university;
create user if not exists 'user'@'%' identified by '';
grant all on university.* to 'user'@'%';
```
- Baixe a última versão do programa na página de [Releases](https://github.com/arthur-cristo-silva/university-crud/releases/latest).
- Execute o programa baixado.