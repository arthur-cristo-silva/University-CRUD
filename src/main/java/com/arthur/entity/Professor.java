package com.arthur.entity;

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