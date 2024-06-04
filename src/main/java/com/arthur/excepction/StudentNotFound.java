package com.arthur.excepction;

public class StudentNotFound extends Exception {

    @Override
    public String toString() {
        return "O(a) aluno(a) pesquisado(a) não foi encontrado(a) no banco de dados.";
    }
}
