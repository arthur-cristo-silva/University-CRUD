package com.arthur;

import com.arthur.dao.ProfessorDAO;
import com.arthur.dao.StudentDAO;
import com.arthur.entity.Professor;
import com.arthur.entity.Student;
import com.arthur.factory.ConnectionFactory;
import com.arthur.frames.MainFrame;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Programa {
    public static void main(String[] args) {
        String professorsTableSql = """
                create table if not exists professors (
                    ra       bigint(20) not null primary key auto_increment,
                    name     varchar(100),
                    phoneNumber      varchar(20),
                    email    varchar(100),
                    workload varchar(2)) auto_increment = 200;""";

        String studentsTableSql = """
                create table if not exists students (
                        ra       bigint(20) not null primary key auto_increment,
                        name     varchar(100),
                        course   varchar(100),
                        periods   int(2),
                        schedule varchar(5),
                        absences int(11)) auto_increment = 100;""";

        try (Connection conn = ConnectionFactory.createConnectionToMySQL(); Statement st = conn.createStatement()) {
            st.executeUpdate(professorsTableSql);
            st.executeUpdate(studentsTableSql);
            // Cria pessoas ao rodar o programa
            ProfessorDAO professorDAO = new ProfessorDAO();
            StudentDAO studentDAO = new StudentDAO();
            professorDAO.save(new Professor("Professor1", "7191111", "one@email.com", 10));
            professorDAO.save(new Professor("Professor2", "7192222", "two@email.com", 20));
            professorDAO.save(new Professor("Professor3", "7193333", "three@email.com", 30));
            professorDAO.save(new Professor("Professor4", "7194444", "four@email.com", 30));
            studentDAO.save(new Student("Student1", "CC", 1, "Manhã", 1));
            studentDAO.save(new Student("Student2", "GTI", 2, "Tarde", 2));
            studentDAO.save(new Student("Student3", "ADS", 3, "Noite", 3));
            new MainFrame();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Banco de dados não encontrado.");
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
