package com.arthur.entity;

import java.util.InputMismatchException;
import java.util.Random;

public class Uc {
    private String code;
    private String name;
    private String type;

    public Uc() {
    }

    public Uc(String code, String name, String type) {
        this.code = code;
        this.name = name;
        this.type = type;
    }

    // Metodo para colher informações do aluno
    public static Uc getUc(String name, String type) throws InputMismatchException {
        Random r = new Random();
        String code = name.toUpperCase().substring(0,4) + (""+r.nextInt(9) + r.nextInt(9) + r.nextInt(9));
        if (name.isEmpty()) {
            throw new InputMismatchException("Por favor, informe o nome da UC.");
        } else if (type.isEmpty()) {
            throw new InputMismatchException("Por favor, informe o tipo da UC.");
        }
        return new Uc(code, name, type);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
