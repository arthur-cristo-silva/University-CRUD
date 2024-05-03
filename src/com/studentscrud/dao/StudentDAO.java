package com.studentscrud.dao;

import com.studentscrud.factory.ConnectionFactory;
import com.studentscrud.objects.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public void save(Student student) throws SQLException {
        String sql = "INSERT INTO students(name, age, course, schedule, absences) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.createConnectionToMySQL(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getCourse());
            ps.setString(4, student.getSchedule());
            ps.setInt(5, student.getAbsences());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> findAll() throws SQLException {
        List<Student> students = new ArrayList<Student>();
        String sql = "SELECT * FROM students";
        try (Connection conn = ConnectionFactory.createConnectionToMySQL(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Student student = new Student();
                student.setRa(rs.getLong("ra"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                student.setCourse(rs.getString("course"));
                student.setSchedule(rs.getString("schedule"));
                student.setAbsences(rs.getInt("absences"));
                students.add(student);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return students;
    }

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
                    studentID.setAge(rs.getInt("age"));
                    studentID.setCourse(rs.getString("course"));
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

    public void update(Student student) throws SQLException {
        String sql = "UPDATE students SET  name = ?, age = ?, course = ?, schedule = ?, absences = ? WHERE ra = ?";
        try (Connection conn = ConnectionFactory.createConnectionToMySQL(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getCourse());
            ps.setString(4, student.getSchedule());
            ps.setInt(5, student.getAbsences());
            ps.setLong(6, student.getRa());
            ps.executeUpdate();
        } catch (Exception f) {
            throw new RuntimeException(f);
        }
    }

    public void delete(Long ra) throws SQLException {
        String sql = "DELETE FROM students WHERE ra = ?";
        try (Connection conn = ConnectionFactory.createConnectionToMySQL(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, ra);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}

