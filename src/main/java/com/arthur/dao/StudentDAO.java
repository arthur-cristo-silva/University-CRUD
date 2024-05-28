package com.arthur.dao;

import com.arthur.factory.ConnectionFactory;
import com.arthur.entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Cadastra novo aluno no banco de dados

    public static void save(Student student) throws SQLException {
        String personSql = "INSERT INTO people(name, type) VALUES(?, 'student')";
        String studentSql = "INSERT INTO students(ra, course, periods, schedule, absences) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement personPs = conn.prepareStatement(personSql, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement studentPs = conn.prepareStatement(studentSql)) {
            personPs.setString(1, student.getName());
            personPs.executeUpdate();
            ResultSet rs = personPs.getGeneratedKeys();
            if (rs.next()) {
                studentPs.setLong(1, rs.getLong(1));
                studentPs.setString(2, student.getCourse());
                studentPs.setInt(3, student.getPeriod());
                studentPs.setString(4, student.getSchedule());
                studentPs.setInt(5, student.getAbsences());
                studentPs.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Retorna todos os alunos cadastrados

    public static List<Student> findAll(boolean aux) throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = aux ?
                "SELECT s.ra, pe.name, s.course, s.periods, s.schedule, s.absences "+
                "FROM students AS s "+
                "INNER JOIN people as pe ON s.ra = pe.ra " +
                "ORDER BY s.ra" :
                "SELECT s.ra, pe.name, s.course, s.periods, s.schedule, s.absences "+
                        "FROM students AS s "+
                        "INNER JOIN people as pe ON s.ra = pe.ra " +
                        "ORDER BY pe.name ASC";;
        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Student student = new Student();
                student.setRa(rs.getLong("ra"));
                student.setName(rs.getString("name"));
                student.setCourse(rs.getString("course"));
                student.setPeriod(rs.getInt("periods"));
                student.setSchedule(rs.getString("schedule"));
                student.setAbsences(rs.getInt("absences"));
                students.add(student);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    // Procura por aluno pelo seu RA

    public static Student findByRA(String ra) throws SQLException {
        ra = ra.isEmpty() ? "0" : ra;
        Student student = new Student();
        String sql = "SELECT s.ra, pe.name, s.course, s.periods, s.schedule, s.absences "+
                "FROM students AS s "+
                "INNER JOIN people AS pe ON s.ra = pe.ra "+
                "WHERE s.ra = ?";
        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, Long.parseLong(ra));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    student.setRa(rs.getLong("ra"));
                    student.setName(rs.getString("name"));
                    student.setCourse(rs.getString("course"));
                    student.setPeriod(rs.getInt("periods"));
                    student.setSchedule(rs.getString("schedule"));
                    student.setAbsences(rs.getInt("absences"));
                }
            }
        } catch (NumberFormatException e) {
            throw e;
        } catch (Exception f) {
            throw new RuntimeException(f);
        }
        return student;
    }

    // Atualiza aluno no banco de dados

    public static void update(Student student) throws SQLException {
        String personSql = "UPDATE people SET name = ? WHERE ra = ?";
        String studentSql = "UPDATE students SET course = ?, periods = ?, schedule = ?, absences = ? WHERE ra = ?";
        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement personPs = conn.prepareStatement(personSql);
             PreparedStatement studentPs = conn.prepareStatement(studentSql)) {
            personPs.setString(1, student.getName());
            personPs.setLong(2, student.getRa());
            personPs.executeUpdate();
            studentPs.setString(1, student.getCourse());
            studentPs.setInt(2, student.getPeriod());
            studentPs.setString(3, student.getSchedule());
            studentPs.setInt(4, student.getAbsences());
            studentPs.setLong(5, student.getRa());
            studentPs.executeUpdate();
        } catch (Exception f) {
            throw new RuntimeException(f);
        }
    }

    // Remove aluno do banco de dados pelo seu RA
    public static void delete(long ra) throws SQLException {
        String studentSql = "DELETE FROM students WHERE ra = ?";
        String personSql = "DELETE FROM people WHERE ra = ?";
        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement personPs = conn.prepareStatement(personSql);
             PreparedStatement studentPs = conn.prepareStatement(studentSql)) {
            personPs.setLong(1, ra);
            studentPs.setLong(1, ra);
            studentPs.executeUpdate();
            personPs.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

