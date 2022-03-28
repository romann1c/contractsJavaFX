package com.romann1c.contracts.database.purejdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final String USERNAME = "root";
    private final String PASSWORD = "123321";

    private static Connection postgresConnection;

    public Connection getConnection() {
        try {
            if (postgresConnection == null || postgresConnection.isClosed()) {

                Class.forName("org.postgresql.Driver");
                postgresConnection = DriverManager.getConnection(
                        URL,
                        USERNAME,
                        PASSWORD
                );
            }
        } catch (Exception e) {
            System.out.println("Exception while getting connection from DriverManager");
        }
        return postgresConnection;
    }

    public void close() {
        try {
            if (!(postgresConnection == null) || !postgresConnection.isClosed()) postgresConnection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
