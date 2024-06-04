package com.arthur.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "R9GhxiJKWTAbkJpP";
    private static final String URL = "jdbc:postgresql://tirelessly-measured-raptor.data-1.use1.tembo.io:5432/postgres";

    // Cria conexão com o banco de dados
    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}