package com.arthur.excepction;

public class ClassNotFound extends Exception {
    @Override
    public String toString() {
        return "A turma pesquisada não foi encontrada no banco de dados.";
    }
}
