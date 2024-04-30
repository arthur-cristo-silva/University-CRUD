package com.studentscrud.dao;

import com.studentscrud.factory.ConnectionFactory;
import com.studentscrud.objects.Student;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public void save(Student student) throws SQLException {

        String sql = "insert into students(name,ra,curso,horario,faltas) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.createConnectionToMySQL(); PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getRa());
            ps.setString(3, student.getCurso());
            ps.setString(4, student.getHorario());
            ps.setInt(5, student.getFaltas());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> getAll() throws SQLException {
        List<Student> students = new ArrayList<Student>();
        String sql = "select * from students";
        try (Connection conn = ConnectionFactory.createConnectionToMySQL(); PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getLong("id"));
                student.setName(rs.getString("name"));
                student.setRa(rs.getString("ra"));
                student.setCurso(rs.getString("curso"));
                student.setHorario(rs.getString("horario"));
                student.setFaltas(rs.getInt("faltas"));
                students.add(student);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    public void update(Student student) throws Exception {
        String sql = "UPDATE students SET name = ?, ra = ?, curso = ?, horario = ?, faltas = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.createConnectionToMySQL(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getRa());
            ps.setString(3, student.getCurso());
            ps.setString(4, student.getHorario());
            ps.setInt(5, student.getFaltas());
            ps.setLong(6, student.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        }
    }

    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = ConnectionFactory.createConnectionToMySQL(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Student getById(Long id) throws Exception {
        Student studentID = new Student();
        String sql = "select * from students where id = ?";
        try (Connection conn = ConnectionFactory.createConnectionToMySQL(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    studentID.setId(rs.getLong("id"));
                    studentID.setName(rs.getString("name"));
                    studentID.setRa(rs.getString("ra"));
                    studentID.setCurso(rs.getString("curso"));
                    studentID.setHorario(rs.getString("horario"));
                    studentID.setFaltas(rs.getInt("faltas"));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return studentID;
    }
}

