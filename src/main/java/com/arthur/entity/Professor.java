package com.arthur.entity;

import java.util.InputMismatchException;

public class Professor extends Person {
    private Long ra;
    private String name;
    private String phoneNumber;
    private String email;
    private Integer workload;

    public Professor() {
    }

    public Professor(String name, String phoneNumber, String email, Integer workload) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.workload = workload;
    }

    public Professor(Long ra, String name, String phoneNumber, String email, Integer workload) {
        this.ra = ra;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.workload = workload;
    }

    public static Professor getProfessor(String name, String phoneNumber, String email, int workload) throws InputMismatchException, NumberFormatException {
        try {
            if (name.isEmpty()) {
                throw new InputMismatchException("Por favor, insira um nome.");
            } else if (phoneNumber.isEmpty()) {
                throw new InputMismatchException("Por favor, insira um numero de telefone.");
            } else if (email.isEmpty()) {
                throw new InputMismatchException("Por favor, insira um email.");
            } else if (workload < 0) {
                throw new InputMismatchException("Por favor, insira um valor positivo em carga horária.");
            }
            return new Professor(name, phoneNumber, email, workload);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Por favor, insira apenas números no campo de carga horária.");
        }
    }

    public static Professor getProfessor(long ra, String name, String email, String phoneNumber, int workload) throws InputMismatchException {

        if (name.isEmpty()) {
            throw new InputMismatchException("Por favor, insira um nome.");
        } else if (email.isEmpty()) {
            throw new InputMismatchException("Por favor, insira um email.");
        } else if (phoneNumber.isEmpty()) {
            throw new InputMismatchException("Por favor, insira um numero de telefone.");
        } else if (workload < 0) {
            throw new InputMismatchException("Por favor, insira um valor positivo em carga horária.");
        }
        return new Professor(ra, name, phoneNumber, email, workload);
    }

    public Long getRa() {
        return ra;
    }

    public void setRa(Long ra) {
        this.ra = ra;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getWorkload() {
        return workload;
    }

    public void setWorkload(Integer workload) {
        this.workload = workload;
    }

}