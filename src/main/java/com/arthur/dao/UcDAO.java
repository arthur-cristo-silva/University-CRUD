package com.arthur.dao;

import com.arthur.entity.Uc;
import com.arthur.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UcDAO {

    // Cadastra UC
    public static void save(Uc uc) throws SQLException {
        String sql = "INSERT INTO disciplines VALUES(?,?,?)";
        try (Connection con = ConnectionFactory.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, uc.getCode());
            ps.setString(2, uc.getName());
            ps.setString(3, uc.getType());
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Uc> getAll() throws SQLException {
        String sql = "SELECT * FROM disciplines";
        List<Uc> ucs = new ArrayList<>();
        try (Connection con = ConnectionFactory.createConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Uc uc = new Uc();
                uc.setCode(rs.getString("code"));
                uc.setName(rs.getString("name"));
                uc.setType(rs.getString("type"));
                ucs.add(uc);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ucs;
    }

    public static List<Uc> getByName(String name) throws Exception {
        String sql = "SELECT * FROM disciplines WHERE name LIKE ?";
        List<Uc> ucs = new ArrayList<>();
        try (Connection con = ConnectionFactory.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%"+name+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Uc uc = new Uc();
                uc.setCode(rs.getString("code"));
                uc.setName(rs.getString("name"));
                uc.setType(rs.getString("type"));
                ucs.add(uc);
            }
        }
        return ucs;
    }

    public static Uc getByCode(String code) throws Exception {
        String sql = "SELECT * FROM disciplines WHERE code = ?";
        Uc uc = new Uc();
        try (Connection con = ConnectionFactory.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    uc.setCode(code);
                    uc.setName(rs.getString("name"));
                    uc.setType(rs.getString("type"));
                }
            }
        }
        return uc;
    }

    public static void update(Uc uc) throws Exception {
        String sql = "UPDATE disciplines SET name = ?, type = ? WHERE code = ?";
        try (Connection con = ConnectionFactory.createConnection();
        PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, uc.getName());
            ps.setString(2, uc.getType());
            ps.setString(3, uc.getCode());
        }
    }

    public static void delete(String code) throws Exception {
        String sql = "DELETE FROM disciplines WHERE code = ?";
        try (Connection con = ConnectionFactory.createConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, code);
            ps.executeUpdate();
        }
    }
}
