package org.twitter.database.config;

import org.twitter.database.exceptions.DatabaseException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Database Connection manager to extract properties and retrieve it.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public class DBConnectionManager {

    private static final Properties properties = new Properties();

    private DBConnectionManager() { }

    static {
        try (final InputStream inputStream = DBConnectionManager.class.getClassLoader()
                .getResourceAsStream("db.properties")) {

            if (inputStream == null) {
                throw new DatabaseException("db.properties file not found in classpath!");
            }

            properties.load(inputStream);

        } catch (IOException ioException) {
            throw new DatabaseException("Can't find properties!", ioException);
        }
    }

    /**
     * To extract property values.
     *
     * @param key           Key of the property
     * @return              Value according to the key
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
