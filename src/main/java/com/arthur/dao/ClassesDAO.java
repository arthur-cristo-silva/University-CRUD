package com.arthur.dao;

import com.arthur.entity.Classes;
import com.arthur.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassesDAO {

    public static void save(Classes classes) throws SQLException {
        String classesSql = "INSERT INTO classes(code, discipline_code, professor_ra) VALUES(?, ?, ?)";
        try (Connection con = ConnectionFactory.createConnection();
             PreparedStatement classPs = con.prepareStatement(classesSql)) {
            classPs.setString(1, classes.getCode());
            classPs.setString(2, classes.getUc());
            classPs.setLong(3, classes.getProfessor());
            classPs.executeUpdate();
        }
    }

    public static void update(Classes classes) throws SQLException {
        String sql = "UPDATE classes SET professor_ra = ?, discipline_code = ? WHERE code = ?";
        try (Connection con = ConnectionFactory.createConnection();
        PreparedStatement classPs = con.prepareStatement(sql)) {
            classPs.setLong(1, classes.getProfessor());
            classPs.setString(2, classes.getUc());
            classPs.setString(3, classes.getCode());
            classPs.executeUpdate();
        }
    }

    public static int count(String code) throws SQLException {
        String sql = "SELECT COUNT(*) FROM students_class WHERE class_code = ?";
        int count = 0;
        try (Connection con = ConnectionFactory.createConnection();
        PreparedStatement classPs = con.prepareStatement(sql)) {
            classPs.setString(1, code);
            try (ResultSet rs = classPs.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        }
        return count;
    }

    public static Classes getByCode(String code) throws SQLException {
        Classes classes = new Classes();
        String classesSql = "SELECT * FROM classes WHERE code = ?";
        try (Connection con = ConnectionFactory.createConnection();
             PreparedStatement ps = con.prepareStatement(classesSql)) {
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    classes.setCode(rs.getString(1));
                    classes.setUc(rs.getString(2));
                    classes.setProfessor(rs.getLong(3));
                }
            }
        } 
        return classes;
    }

    public static void addStudent(String code, long ra) throws SQLException {
        String studentClassSql = "INSERT INTO students_class(class_code, student_ra) VALUES(?, ?)";
        try (Connection con = ConnectionFactory.createConnection();
             PreparedStatement studentPs = con.prepareStatement(studentClassSql)) {
            studentPs.setString(1, code);
            studentPs.setLong(2, ra);
            studentPs.executeUpdate();
        }
    }

    public static void removeStudent(String code, long student) throws SQLException {
        String studentClassSql = "DELETE FROM students_class WHERE student_ra = ? AND class_code = ?";
        try (Connection con = ConnectionFactory.createConnection();
             PreparedStatement classPs = con.prepareStatement(studentClassSql)) {
            classPs.setLong(1, student);
            classPs.setString(2, code);
            classPs.executeUpdate();
        }
    }

    public static void delete(String code) throws SQLException {
        String classesSql = "DELETE FROM classes WHERE classes_code = ?";
        String studentClassSql = "DELETE FROM student_classes WHERE classes_code = ?";
        try (Connection con = ConnectionFactory.createConnection();
             PreparedStatement classPs = con.prepareStatement(classesSql);
             PreparedStatement studentPs = con.prepareStatement(studentClassSql)) {
            classPs.setString(1, code);
            classPs.executeUpdate();
            studentPs.setString(1, code);
            studentPs.executeUpdate();
        }
    }

    public static List<Classes> getAll() throws SQLException {
        String sql = "SELECT * FROM classes";
        List<Classes> list = new ArrayList<>();
        try(Connection con = ConnectionFactory.createConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                Classes classes = new Classes();
                classes.setCode(rs.getString(1));
                classes.setUc(rs.getString(2));
                classes.setProfessor(rs.getLong(3));
                list.add(classes);
            }
        }
        return list;
    }
}
