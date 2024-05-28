package com.arthur.factory;

import com.arthur.entity.Uc;

public class RandomUc {
    public static Uc getUc() {
        Uc uc = new Uc();
        uc.setName(RandomTemplates.getRandomDiscipline());
        uc.setCode(RandomTemplates.getRandomCode(uc.getName()));
        uc.setType(RandomTemplates.getRandomType());
        return uc;
    }
}
