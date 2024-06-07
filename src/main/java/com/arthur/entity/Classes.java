package com.arthur.entity;

public class Classes {
    private String code;
    private Long professor;
    private String professorName;
    private String uc;
    private String ucName;
    private String ucType;
    private Long amountOfStudents;

    public Classes() {
    }

    public Classes(String code, Long professor, String uc) {
        this.code = code;
        this.professor = professor;
        this.uc = uc;
    }

    public Classes(String code, String professorName, String ucName, String ucType, Long amountOfStudents) {
        this.code = code;
        this.professorName = professorName;
        this.ucName = ucName;
        this.ucType = ucType;
        this.amountOfStudents = amountOfStudents;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getProfessor() {
        return professor;
    }

    public void setProfessor(Long professor) {
        this.professor = professor;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }

    public String getUcName() {
        return ucName;
    }

    public void setUcName(String ucName) {
        this.ucName = ucName;
    }

    public String getUcType() {
        return ucType;
    }

    public void setUcType(String ucType) {
        this.ucType = ucType;
    }

    public Long getAmountOfStudents() {
        return amountOfStudents;
    }

    public void setAmountOfStudents(Long amountOfStudents) {
        this.amountOfStudents = amountOfStudents;
    }
}
