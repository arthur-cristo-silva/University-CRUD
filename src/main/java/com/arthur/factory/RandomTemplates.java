package com.arthur.factory;

import java.util.Random;

public class RandomTemplates {
    private final static String[] NAMES = {"Miguel", "Arthur", "Heitor", "Helena", "Alice", "Laura", "Davi", "Gabriel", "Valentina", "Maria Eduarda", "Pedro", "Matheus", "Bernardo", "Lorenzo", "Rafael", "Beatriz", "Nicolas", "Lucas", "Ana Clara", "Joaquim"};
    private final static String[] LAST_NAMES = {"Silva", "Santos", "Oliveira", "Souza", "Rodrigues", "Ferreira", "Alves", "Pereira", "Lima", "Gomes", "Costa", "Ribeiro", "Martins", "Carvalho", "Araújo", "Almeida", "Melo", "Barros", "Freitas", "Vieira"};
    private final static String[] EMAILS = {"@gmail.com", "@yahoo.com", "@hotmail.com", "@outlook.com", "@icloud.com", "@uol.com.br", "@bol.com.br", "@terra.com.br", "@r7.com", "@live.com", "@gov.br", "@mail.com", "@aol.com", "@zoho.com", "@gmx.com", "@protonmail.com", "@me.com", "@msn.com", "@edu.br", "@email.com"};
    private final static String[] COURSES = {"Administração", "Direito", "Medicina", "Engenharia Civil", "Psicologia", "Enfermagem", "Ciências Contábeis", "Pedagogia", "Arquitetura e Urbanismo", "Educação Física", "Farmácia", "Fisioterapia", "Odontologia", "Engenharia de Produção", "Ciência da Computação", "Biomedicina", "Nutrição", "Jornalismo", "Publicidade e Propaganda", "Serviço Social"};
    private final static String[] SCHEDULES = {"Manhã", "Tarde", "Noite"};
    private final static String[] DISCIPLINES = {"Cálculo 1", "Cálculo 2", "Cálculo 3", "Geometria Analítica", "Álgebra Linear", "Estatística e Probabilidade", "Física 1", "Física 2", "Química Geral", "Química Orgânica", "Biologia Celular", "Genética", "Programação 1", "Programação 2", "Estruturas de Dados", "Algoritmos", "Engenharia de Software", "Banco de Dados", "Redes de Computadores", "Sistemas Operacionais", "Segurança da Informação", "Inteligência Artificial", "Modelagem de Software", "Gestão de Projetos", "Pesquisa Operacional", "Análise de Sistemas", "Administração Financeira", "Marketing Digital", "Contabilidade Gerencial", "Direito Empresarial", "Psicologia Organizacional", "Sociologia Aplicada", "Filosofia Contemporânea", "História do Brasil", "Geopolítica", "Literatura Brasileira", "Metodologia Científica", "Ética Profissional", "Nutrição e Dietética", "Farmacologia Clínica", "Enfermagem Pediátrica", "Medicina Interna", "Odontologia Preventiva", "Fisioterapia Esportiva", "Arquitetura e Urbanismo", "Design de Interiores", "Artes Visuais Contemporâneas", "Teoria Musical", "Direção Teatral", "Pedagogia Inclusiva", "Ciências Políticas", "Antropologia Cultural", "Comunicação Visual", "Jornalismo Investigativo", "Publicidade e Propaganda"};
    private final static String[] TYPES = {"Téorica", "Prática", "Busca Ativa"};
    private final static Random r = new Random();


    public static String getRandomName() {
        return String.format("%s %s %s", NAMES[r.nextInt(NAMES.length)], LAST_NAMES[r.nextInt(LAST_NAMES.length)], LAST_NAMES[r.nextInt(LAST_NAMES.length)]);
    }

    public static String getRandomCourse() {
        return COURSES[r.nextInt(COURSES.length)];
    }

    public static String getRandomSchedule() {
        return SCHEDULES[r.nextInt(SCHEDULES.length)];
    }

    public static String getRandomPhoneNumber() {
        int ddd = r.nextInt(99) + 11;
        int number = r.nextInt(99999999) + 10000000;
        return String.format("+55 (%02d) 9%04d-%04d", ddd, number / 10000, number % 10000);
    }

    public static String getRandomEmail() {
        return NAMES[r.nextInt(NAMES.length)].toLowerCase() + EMAILS[r.nextInt(EMAILS.length)];
    }

    public static String getRandomDiscipline() {
        return DISCIPLINES[r.nextInt(DISCIPLINES.length)];
    }

    public static String getRandomCode(String name) {
        return name.toUpperCase().substring(0,4) + (r.nextInt(9) + r.nextInt(9) + r.nextInt(9));
    }

    public static String getRandomType() {
        return TYPES[r.nextInt(TYPES.length)];
    }

}
