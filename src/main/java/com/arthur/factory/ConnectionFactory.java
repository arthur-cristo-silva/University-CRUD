package com.arthur.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "almQh0EzEQi676fv";
    private static final String URL = "jdbc:postgresql://vengefully-spotless-lagomorph.data-1.use1.tembo.io:5432/postgres";

    // Cria conexão com o banco de dados
    public static Connection createConnection() throws Exception {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}