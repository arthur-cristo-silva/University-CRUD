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
        }
    }

    // Retorna todos os alunos cadastrados

    public static List<Student> findAll(boolean aux) throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = aux ?
                "SELECT s.ra, pe.name, s.course, s.periods, s.schedule, s.absences " +
                        "FROM students AS s " +
                        "INNER JOIN people as pe ON s.ra = pe.ra " +
                        "ORDER BY s.ra" :
                "SELECT s.ra, pe.name, s.course, s.periods, s.schedule, s.absences " +
                        "FROM students AS s " +
                        "INNER JOIN people as pe ON s.ra = pe.ra " +
                        "ORDER BY pe.name ASC";
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
        }
        return students;
    }

    public static List<Student> findAll() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT s.ra, pe.name, s.course, s.periods, s.schedule, s.absences " +
                "FROM students AS s " +
                "INNER JOIN people as pe ON s.ra = pe.ra " +
                "ORDER BY s.ra";
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
        }
        return students;
    }

    // Procura por aluno pelo seu RA

    public static Student findByRA(String ra) throws SQLException {
        ra = ra.isEmpty() ? "0" : ra;
        Student student = new Student();
        String sql = "SELECT s.ra, pe.name, s.course, s.periods, s.schedule, s.absences " +
                "FROM students AS s " +
                "INNER JOIN people AS pe ON s.ra = pe.ra " +
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
        }
        return student;
    }

    public static List<Student> getByName(String name) throws SQLException {
        String sql = "SELECT s.ra, pe.name, s.course, s.periods, s.schedule, s.absences " +
                "FROM students AS s " +
                "INNER JOIN people as pe ON s.ra = pe.ra " +
                "WHERE pe.name LIKE ?";
        List<Student> students = new ArrayList<>();
        try (Connection con = ConnectionFactory.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
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
        }
        return students;
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
        }
    }

    // if aux == true  return students out of class, else return in class
    public static List<Student> getStudentsClass(String class_code, boolean aux) throws SQLException {
        String sql = aux ? "SELECT pe.ra, pe.name, s.course " +
                "FROM people AS pe " +
                "INNER JOIN students s ON pe.ra = s.ra " +
                "LEFT JOIN students_class sc ON pe.ra = sc.student_ra AND sc.class_code = ? " +
                "WHERE sc.student_ra IS NULL" :
                "SELECT pe.ra, pe.name, s.course " +
                        "FROM people AS pe " +
                        "INNER JOIN students s ON pe.ra = s.ra " +
                        "LEFT JOIN students_class sc ON pe.ra = sc.student_ra AND sc.class_code = ? " +
                        "WHERE sc.student_ra IS NOT NULL";
        List<Student> students = new ArrayList<>();
        try (Connection con = ConnectionFactory.createConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, class_code);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setRa(rs.getLong("ra"));
                student.setName(rs.getString("name"));
                student.setCourse(rs.getString("course"));
                students.add(student);
            }
        }
        return students;
    }

}

