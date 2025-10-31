package org.twitter.database.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private static final String URL = "jdbc:postgresql://localhost:5432/twitter";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    private DBConnect() { }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
