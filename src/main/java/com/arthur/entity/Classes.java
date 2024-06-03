package com.arthur.entity;

public class Classes {
    private String code;
    private String professor;
    private String uc;

    public Classes() {
    }

    public Classes(String code, String professor, String uc) {
        this.code = code;
        this.professor = professor;
        this.uc = uc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }
}
