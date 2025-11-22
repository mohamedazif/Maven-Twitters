package org.twitter.database.exceptions;

/**
 * Exception class for Database related exception.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public final class DatabaseException extends RuntimeException {

    public DatabaseException(final String message) {
        super(message);
    }

    public DatabaseException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
