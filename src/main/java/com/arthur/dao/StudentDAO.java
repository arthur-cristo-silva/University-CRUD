package com.arthur.dao;

import com.arthur.factory.ConnectionFactory;
import com.arthur.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements DAO<Student> {

    // Cadastra novo aluno no banco de dados
    @Override
    public void save(Student student) throws SQLException {
        String sql = "INSERT INTO students(name, course, periods, schedule, absences) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.createConnectionToMySQL(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getCourse());
            ps.setInt(3, student.getPeriod());
            ps.setString(4, student.getSchedule());
            ps.setInt(5, student.getAbsences());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Retorna todos os alunos cadastrados
    @Override
    public List<Student> findAll() throws SQLException {
        List<Student> students = new ArrayList<Student>();
        String sql = "SELECT * FROM students";
        try (Connection conn = ConnectionFactory.createConnectionToMySQL();
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
    @Override
    public Student findByRA(String ra) throws SQLException {
        ra = ra.isEmpty() ? "0" : ra;
        Student studentID = new Student();
        String sql = "select * from students where ra = ?";
        try (Connection conn = ConnectionFactory.createConnectionToMySQL(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, Long.parseLong(ra));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    studentID.setRa(rs.getLong("ra"));
                    studentID.setName(rs.getString("name"));
                    studentID.setCourse(rs.getString("course"));
                    studentID.setPeriod(rs.getInt("periods"));
                    studentID.setSchedule(rs.getString("schedule"));
                    studentID.setAbsences(rs.getInt("absences"));
                }
            }
        } catch (NumberFormatException e) {
            throw e;
        } catch (Exception f) {
            throw new RuntimeException(f);
        }
        return studentID;
    }

    // Atualiza aluno no banco de dados
    @Override
    public void update(Student student) throws SQLException {
        String sql = "UPDATE students SET  name = ?, course = ?, periods = ?, schedule = ?, absences = ? WHERE ra = ?";
        try (Connection conn = ConnectionFactory.createConnectionToMySQL(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getCourse());
            ps.setInt(3, student.getPeriod());
            ps.setString(4, student.getSchedule());
            ps.setInt(5, student.getAbsences());
            ps.setLong(6, student.getRa());
            ps.executeUpdate();
        } catch (Exception f) {
            throw new RuntimeException(f);
        }
    }

    // Remove aluno do banco de dados pelo seu RA
    @Override
    public void delete(long ra) throws SQLException {
        String sql = "DELETE FROM students WHERE ra = ?";
        try (Connection conn = ConnectionFactory.createConnectionToMySQL(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, ra);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

