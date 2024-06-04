package com.arthur.excepction;

public class ProfessorNotFound extends Exception {

    @Override
    public String toString() {
        return "O(a) professor(a) pesquisado(a) não foi encontrado(a) no banco de dados.";
    }
}
