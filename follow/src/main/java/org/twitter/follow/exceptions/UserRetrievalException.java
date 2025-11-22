package org.twitter.follow.exceptions;

/**
 * Custom Exception for catching exception while retrieving user information.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public class UserRetrievalException extends RuntimeException {
    public UserRetrievalException(final String message) {
        super(message);
    }
}
