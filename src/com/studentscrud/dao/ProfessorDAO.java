package com.studentscrud.dao;

import com.studentscrud.factory.ConnectionFactory;
import com.studentscrud.objects.Professor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO implements DAO<Professor> {

    @Override
    public void save(Professor professor) throws SQLException {
        String sql = "INSERT INTO professors(name, age, email, workload) VALUES(?,?,?,?)";
        try(Connection conn = ConnectionFactory.createConnectionToMySQL(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, professor.getName());
            ps.setInt(2, professor.getAge());
            ps.setString(3, professor.getEmail());
            ps.setInt(4, professor.getWorkload());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Professor> findAll() throws SQLException {
        List<Professor> professors = new ArrayList<Professor>();
        String sql = "SELECT * FROM professors";
        try(Connection conn = ConnectionFactory.createConnectionToMySQL();
            PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Professor professor = new Professor();
                professor.setRa(rs.getLong("ra"));
                professor.setName(rs.getString("name"));
                professor.setAge(rs.getInt("age"));
                professor.setEmail(rs.getString("email"));
                professor.setWorkload(rs.getInt("workload"));
                professors.add(professor);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return professors;
    }

    @Override
    public Professor findByRA(String ra) throws SQLException {
        ra = ra.isEmpty() ? "0" : ra;
        Professor professor = new Professor();
        String sql = "SELECT * FROM professors WHERE ra = ?";
        try(Connection conn = ConnectionFactory.createConnectionToMySQL();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, Long.parseLong(ra));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    professor.setRa(rs.getLong("ra"));
                    professor.setName(rs.getString("name"));
                    professor.setAge(rs.getInt("age"));
                    professor.setEmail(rs.getString("email"));
                    professor.setWorkload(rs.getInt("workload"));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return professor;
    }

    @Override
    public void update(Professor professor) throws SQLException {
        String sql = "UPDATE professors SET name = ?, age = ?, email = ?, workload = ? WHERE ra = ?";
        try(Connection conn = ConnectionFactory.createConnectionToMySQL();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, professor.getName());
            ps.setInt(2, professor.getAge());
            ps.setString(3, professor.getEmail());
            ps.setInt(4, professor.getWorkload());
            ps.setLong(5, professor.getRa());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long ra) throws SQLException {
        String sql = "DELETE FROM professors WHERE ra = ?";
        try(Connection conn = ConnectionFactory.createConnectionToMySQL();
        PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, ra);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
