package com.arthur.factory;

import com.arthur.entity.Professor;

import java.util.Random;

public class RandomProfessor {
    public static Professor getProfessor() {
        Random r = new Random();
        Professor professor = new Professor();
        professor.setName(RandomTemplates.getRandomName());
        professor.setPhoneNumber(RandomTemplates.getRandomPhoneNumber());
        professor.setEmail(RandomTemplates.getRandomEmail(professor.getName().split(" ")[0]));
        professor.setWorkload(r.nextInt(44));
        return professor;
    }

}
