package com.studentscrud.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static final String USERNAME = "user";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/university";

    public static Connection createConnectionToMySQL() throws Exception {
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection;
    }

    public static void main(String[] args) throws Exception {
        Connection con = createConnectionToMySQL();
        if (con != null) {
            System.out.println("Conexão com sucesso");
            con.close();
        }
    }

}
