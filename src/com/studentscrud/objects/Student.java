package com.studentscrud.objects;

public class Student {
    private Long ra;
    private String name;
    private int age;
    private String course;
    private String schedule;
    private int absences;

    public Student() {}

    public Student(String name, int age, String course, String schedule, int absences) {
        this.name = name;
        this.age = age;
        this.course = course;
        this.schedule = schedule;
        this.absences = absences;
    }

    public Student(Long ra, String name, int age, String course, String schedule, int absences) {
        this.ra = ra;
        this.name = name;
        this.age = age;
        this.course = course;
        this.schedule = schedule;
        this.absences = absences;
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public int getAbsences() {
        return absences;
    }

    public void setAbsences(int absences) {
        this.absences = absences;
    }
}
