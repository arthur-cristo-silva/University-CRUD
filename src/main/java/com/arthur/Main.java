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

        String disciplineTableSql = """
                CREATE TABLE IF NOT EXISTS disciplines (
                code VARCHAR(100) NOT NULL UNIQUE PRIMARY KEY,
                name     VARCHAR(100),
                type VARCHAR(100));""";

        String classStudentsTableSql = """
                CREATE TABLE IF NOT EXISTS students_class (
                student_ra BIGINT NOT NULL,
                class_code VARCHAR(100) NOT NULL,
                PRIMARY KEY(student_ra, class_code),
                FOREIGN KEY (student_ra) REFERENCES students(ra),
                FOREIGN KEY (class_code) REFERENCES classes(code));""";

        String classesTableSql = """
                CREATE TABLE IF NOT EXISTS classes (
                code VARCHAR(100) NOT NULL UNIQUE PRIMARY KEY,
                discipline_code VARCHAR(100),
                professor_ra BIGINT NOT NULL,
                FOREIGN KEY (professor_ra) REFERENCES professors(ra),
                FOREIGN KEY (discipline_code) REFERENCES disciplines(code));""";

        try (Connection conn = ConnectionFactory.createConnection(); Statement st = conn.createStatement()) {
            // Cria tabela de professores e alunos
            st.executeUpdate(peopleTableSql);
            st.executeUpdate(professorsTableSql);
            st.executeUpdate(studentsTableSql);
            st.executeUpdate(disciplineTableSql);
            st.executeUpdate(classesTableSql);
            st.executeUpdate(classStudentsTableSql);
            new MainFrame();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Banco de dados não encontrado.");
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
