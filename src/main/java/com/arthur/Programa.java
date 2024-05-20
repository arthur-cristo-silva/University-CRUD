package com.arthur;

import com.arthur.factory.ConnectionFactory;
import com.arthur.frames.MainFrame;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Programa {
    public static void main(String[] args) {
        String professorsTableSql = """
                create table if not exists professors (
                    ra       bigint(20) not null primary key auto_increment,
                    name     varchar(100),
                    phoneNumber      varchar(20),
                    email    varchar(100),
                    workload varchar(2)) auto_increment = 200;""";

        String studentsTableSql = """
                create table if not exists students (
                        ra       bigint(20) not null primary key auto_increment,
                        name     varchar(100),
                        course   varchar(100),
                        periods   int(2),
                        schedule varchar(5),
                        absences int(11)) auto_increment = 100;""";

        try (Connection conn = ConnectionFactory.createConnectionToMySQL(); Statement st = conn.createStatement()) {
            st.executeUpdate(professorsTableSql);
            st.executeUpdate(studentsTableSql);
            new MainFrame();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Banco de dados não encontrado.");
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
