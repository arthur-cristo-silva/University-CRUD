package com.arthur.excepction;

public class UcNotFound extends Exception {

    @Override
    public String toString() {
        return "A UC pesquisada não foi encontrada no banco de dados.";
    }
}
