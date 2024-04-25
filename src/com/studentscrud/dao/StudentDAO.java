package com.studentscrud.dao;

import com.studentscrud.factory.ConnectionFactory;
import com.studentscrud.objects.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAO {

    public void save(Student student) throws SQLException {
        String sql = "insert into students(name,ra,curso,horario,faltas) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, student.getName());
            ps.setString(2, student.getRa());
            ps.setString(3, student.getCurso());
            ps.setString(4, student.getHorario());
            ps.setInt(5, student.getFaltas());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
