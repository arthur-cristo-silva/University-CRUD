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

public class Main {
    public static void main(String[] args) {

        String peopleTableSql = """
                CREATE TABLE IF NOT EXISTS people (
                    ra       BIGSERIAL PRIMARY KEY,
                    name     VARCHAR(100),
                    type     VARCHAR(10));""";

        String professorsTableSql = """
                CREATE TABLE IF NOT EXISTS professors (
                    ra            BIGINT NOT NULL PRIMARY KEY,
                    phoneNumber   VARCHAR(100),
                    email         VARCHAR(100),
                    workload      INT,
                    FOREIGN KEY (ra) REFERENCES people(ra));""";

        String studentsTableSql = """
                CREATE TABLE IF NOT EXISTS students (
                    ra        BIGINT NOT NULL PRIMARY KEY,
                    course    VARCHAR(100),
                    periods   INT,
                    schedule  VARCHAR(5),
                    absences  INT,
                    FOREIGN KEY (ra) REFERENCES people(ra));""";

        try (Connection conn = ConnectionFactory.createConnection(); Statement st = conn.createStatement()) {
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
