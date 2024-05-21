package com.arthur.factory;

import java.util.Random;

public class RandomTemplates {
    private final static String[] names = {"Miguel", "Arthur", "Heitor", "Helena", "Alice", "Laura", "Davi", "Gabriel", "Valentina", "Maria Eduarda", "Pedro", "Matheus", "Bernardo", "Lorenzo", "Rafael", "Beatriz", "Nicolas", "Lucas", "Ana Clara", "Joaquim"};
    private final static String[] lastNames = {"Silva", "Santos", "Oliveira", "Souza", "Rodrigues", "Ferreira", "Alves", "Pereira", "Lima", "Gomes", "Costa", "Ribeiro", "Martins", "Carvalho", "Araújo", "Almeida", "Melo", "Barros", "Freitas", "Vieira"};
    private final static String[] emails = {"@gmail.com", "@yahoo.com", "@hotmail.com", "@outlook.com", "@icloud.com", "@uol.com.br", "@bol.com.br", "@terra.com.br", "@r7.com", "@live.com", "@gov.br", "@mail.com", "@aol.com", "@zoho.com", "@gmx.com", "@protonmail.com", "@me.com", "@msn.com", "@edu.br", "@email.com"};
    private final static String[] courses = {"Administração", "Direito", "Medicina", "Engenharia Civil", "Psicologia", "Enfermagem", "Ciências Contábeis", "Pedagogia", "Arquitetura e Urbanismo", "Educação Física", "Farmácia", "Fisioterapia", "Odontologia", "Engenharia de Produção", "Ciência da Computação", "Biomedicina", "Nutrição", "Jornalismo", "Publicidade e Propaganda", "Serviço Social"};
    private final static String[] schedules = {"Manhã", "Tarde", "Noite"};
    private final static Random r = new Random();

    public static String getRandomName() {
        return String.format("%s %s %s", names[r.nextInt(names.length)], lastNames[r.nextInt(lastNames.length)], lastNames[r.nextInt(lastNames.length)]);
    }

    public static String getRandomCourse() {
        return courses[r.nextInt(courses.length)];
    }

    public static String getRandomSchedule() {
        return schedules[r.nextInt(schedules.length)];
    }

    public static String getRandomPhoneNumber() {
        int ddd = r.nextInt(99)+11;
        int number = r.nextInt(99999999)+10000000;
        return String.format("+55 (%02d) 9%04d-%04d", ddd, number/10000, number % 10000);
    }

    public static String getRandomEmail() {
        return names[r.nextInt(names.length)].toLowerCase()+emails[r.nextInt(emails.length)];
    }

}
