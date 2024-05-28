package com.arthur.dao;

import com.arthur.factory.ConnectionFactory;
import com.arthur.entity.Professor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    //Cadastra novo professor no banco de dados
    public static void save(Professor professor) throws SQLException {
        String personSql = "INSERT INTO people(name, type) VALUES(?, 'professor')";
        String professorSql = "INSERT INTO professors(ra, phoneNumber, email, workload) VALUES(?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement personPs = conn.prepareStatement(personSql, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement professorPs = conn.prepareStatement(professorSql)) {
            personPs.setString(1, professor.getName());
            personPs.executeUpdate();
            ResultSet rs = personPs.getGeneratedKeys();
            if (rs.next()) {
                professorPs.setLong(1, rs.getLong(1));
                professorPs.setString(2, professor.getPhoneNumber());
                professorPs.setString(3, professor.getEmail());
                professorPs.setInt(4, professor.getWorkload());
                professorPs.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Professor> findAll(Boolean aux) throws SQLException {
        List<Professor> professors = new ArrayList<>();
        String sql = aux ?
                "SELECT p.ra, pe.name, p.phoneNumber, p.email, p.workload " +
                        "FROM professors AS p " +
                        "INNER JOIN people AS pe ON p.ra = pe.ra " +
                        "ORDER BY p.ra" :
                "SELECT p.ra, pe.name, p.phoneNumber, p.email, p.workload " +
                        "FROM professors AS p " +
                        "INNER JOIN people AS pe ON p.ra = pe.ra " +
                        "ORDER BY pe.name ASC";
        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Professor professor = new Professor();
                professor.setRa(rs.getLong("ra"));
                professor.setName(rs.getString("name"));
                professor.setPhoneNumber(rs.getString("phoneNumber"));
                professor.setEmail(rs.getString("email"));
                professor.setWorkload(rs.getInt("workload"));
                professors.add(professor);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return professors;
    }

    public static Professor findByRA(String ra) throws SQLException {
        ra = ra.isEmpty() ? "0" : ra;
        Professor professor = new Professor();
        String sql = "SELECT p.ra, pe.name, p.phoneNumber, p.email, p.workload " +
                "FROM professors AS p " +
                "INNER JOIN people AS pe ON p.ra = pe.ra " +
                "WHERE p.ra = ?";
        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, Long.parseLong(ra));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    professor.setRa(rs.getLong("ra"));
                    professor.setName(rs.getString("name"));
                    professor.setPhoneNumber(rs.getString("phoneNumber"));
                    professor.setEmail(rs.getString("email"));
                    professor.setWorkload(rs.getInt("workload"));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return professor;
    }

    public static void update(Professor professor) throws SQLException {
        String sqlPeople = "UPDATE people SET name = ? WHERE ra = ?";
        String sqlProfessors = "UPDATE professors SET phoneNumber = ?, email = ?, workload = ? WHERE ra = ?";
        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement psPeople = conn.prepareStatement(sqlPeople);
             PreparedStatement psProfessors = conn.prepareStatement(sqlProfessors)) {
            psPeople.setString(1, professor.getName());
            psPeople.setLong(2, professor.getRa());
            psPeople.executeUpdate();
            psProfessors.setString(1, professor.getPhoneNumber());
            psProfessors.setString(2, professor.getEmail());
            psProfessors.setInt(3, professor.getWorkload());
            psProfessors.setLong(4, professor.getRa());
            psProfessors.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(long ra) throws SQLException {
        String sqlProfessors = "DELETE FROM professors WHERE ra = ?";
        String sqlPeople = "DELETE FROM people WHERE ra = ?";
        try (Connection conn = ConnectionFactory.createConnection();
             PreparedStatement psProfessors = conn.prepareStatement(sqlProfessors);
             PreparedStatement psPeople = conn.prepareStatement(sqlPeople)) {

            psProfessors.setLong(1, ra);
            psProfessors.executeUpdate();

            psPeople.setLong(1, ra);
            psPeople.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
