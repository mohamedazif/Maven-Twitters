package org.twitter.database.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides connection to the PostgreSQL Database.
 *
 * @version             1.0
 * @author              Mohamed Abdul Azif
 */
public class DBConnect {

    private static final String URL = DBConnectionManager.getProperty("db.url");
    private static final String USER = DBConnectionManager.getProperty("db.username");
    private static final String PASSWORD = DBConnectionManager.getProperty("db.password");

    private DBConnect() { }

    /**
     * To connect to the database.
     *
     * @return                  Connection object
     * @throws SQLException     Failed connection throws exception
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
