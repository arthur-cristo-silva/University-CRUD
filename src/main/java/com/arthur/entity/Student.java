package com.arthur.entity;

import java.util.InputMismatchException;
import java.util.Objects;

public class Student extends Person {
    private Long ra;
    private String name;
    private String course;
    private Integer period;
    private String schedule;
    private Integer absences;

    public Student() {}

    public Student(String name, String course, Integer period, String schedule, Integer absences) {
        this.name = name;
        this.course = course;
        this.period = period;
        this.schedule = schedule;
        this.absences = absences;
    }

    public Student(Long ra, String name, String course, Integer period, String schedule, Integer absences) {
        this.ra = ra;
        this.name = name;
        this.course = course;
        this.period = period;
        this.schedule = schedule;
        this.absences = absences;
    }

    public static Student getStudent(String name, String course, int period, String schedule, int absences) throws Exception {
        if (name.isEmpty()) {
            throw new InputMismatchException("Por favor, digite um nome.");
        } else if (course.isEmpty()) {
            throw new InputMismatchException("Por favor, digite um curso.");
        }
        return new Student(name, course, period, schedule, absences);
    }

    public static Student getStudent(long ra, String name, String course, int period, String schedule, int absences) throws Exception {
        period = period == 0 ? 1 : period;
        if (name.isEmpty()) {
            throw new InputMismatchException("Por favor, digite um nome.");
        } else if (course.isEmpty()) {
            throw new InputMismatchException("Por favor, digite um curso.");
        }
        return new Student(ra, name, course, period, schedule, absences);
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Integer getAbsences() {
        return absences;
    }

    public void setAbsences(Integer absences) {
        this.absences = absences;
    }

}
