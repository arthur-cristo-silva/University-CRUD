package com.studentscrud.objects;

public class Professor {
    private Long id;
    private String name;
    private String ra;
    private String email;

    public Professor() {
    }

    public Professor(String name, String ra, String email) {
        this.name = name;
        this.ra = ra;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
