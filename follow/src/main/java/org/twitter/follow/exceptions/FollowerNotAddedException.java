package org.twitter.follow.exceptions;

/**
 * Custom Exception for follower not added to the database.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public class FollowerNotAddedException extends RuntimeException {
    public FollowerNotAddedException(final String message) {
        super(message);
    }
}
