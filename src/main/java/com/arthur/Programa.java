package com.arthur;

import com.arthur.dao.ProfessorDAO;
import com.arthur.dao.StudentDAO;
import com.arthur.factory.ConnectionFactory;
import com.arthur.factory.RandomProfessor;
import com.arthur.factory.RandomStudent;
import com.arthur.frames.MainFrame;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Programa {
    public static void main(String[] args) {

        String peopleTableSql = """
                create table if not exists people (
                    ra       bigint(20) not null primary key auto_increment,
                    name     varchar(100),
                    type     varchar(10));""";

        String professorsTableSql = """
                create table if not exists professors (
                    ra       bigint(20) not null primary key,
                    phoneNumber      varchar(20),
                    email    varchar(100),
                    workload varchar(2),
                    foreign key (ra) references people(ra));""";

        String studentsTableSql = """
                create table if not exists students (
                    ra       bigint(20) not null primary key,
                    course   varchar(100),
                    periods   int(2),
                    schedule varchar(5),
                    absences int(11),
                    foreign key (ra) references people(ra));""";

        try (Connection conn = ConnectionFactory.createConnectionToMySQL(); Statement st = conn.createStatement()) {
            // Cria tabela de professores e alunos
            st.executeUpdate(peopleTableSql);
            st.executeUpdate(professorsTableSql);
            st.executeUpdate(studentsTableSql);
            boolean control = false;
            if (control) {
                ProfessorDAO professorDAO = new ProfessorDAO();
                StudentDAO studentDAO = new StudentDAO();
                for (int i = 0; i < 4; i++) {
                    professorDAO.save(RandomProfessor.getProfessor());
                    studentDAO.save(RandomStudent.getStudent());
                }
            }
            new MainFrame();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Banco de dados não encontrado.");
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
