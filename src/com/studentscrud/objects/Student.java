package com.studentscrud.objects;

public class Student {
    private Long id;
    private String name;
    private String ra;
    private String curso;
    private String horario;
    private int faltas;

    public Student() {}

    public Student(String name, String ra, String curso, String horario, int faltas) {
        this.name = name;
        this.ra = ra;
        this.curso = curso;
        this.horario = horario;
        this.faltas = faltas;
    }

    public Student(Long id, String name, String ra, String curso, String horario, int faltas) {
        this.id = id;
        this.name = name;
        this.ra = ra;
        this.curso = curso;
        this.horario = horario;
        this.faltas = faltas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
