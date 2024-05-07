package com.studentscrud.objects;

public class Professor {
    private Long ra;
    private String name;
    private int age;
    private String email;
    private int workload;

    public Professor() {
    }

    public Professor(String name, int age, String email, int workload) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.workload = workload;
    }

    public Professor(Long ra, String name, int age, String email, int workload) {
        this.ra = ra;
        this.name = name;
        this.age = age;
        this.email = email;
        this.workload = workload;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getWorkload() {
        return workload;
    }

    public void setWorkload(int workload) {
        this.workload = workload;
    }
}