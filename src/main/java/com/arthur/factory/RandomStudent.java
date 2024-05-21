package com.arthur.factory;

import com.arthur.entity.Student;

import java.util.Random;

public class RandomStudent {

    public static Student getStudent() {
        Random r = new Random();
        Student student = new Student();
        student.setName(RandomTemplates.getRandomName());
        student.setCourse(RandomTemplates.getRandomCourse());
        student.setPeriod(r.nextInt(12));
        student.setSchedule(RandomTemplates.getRandomSchedule());
        student.setAbsences(r.nextInt(100));
        return student;
    }
}
