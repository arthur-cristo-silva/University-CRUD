package com.arthur.entity;

public class Person {
    private Long ra;
    private String name;
    private String type;

    public Person() {
    }

    public Person(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Person(Long ra, String name, String type) {
        this.ra = ra;
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
