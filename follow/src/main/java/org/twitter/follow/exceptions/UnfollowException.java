package org.twitter.follow.exceptions;

/**
 * Custom Exception for catching exception while unfollowing a user.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public class UnfollowException extends RuntimeException {
    public UnfollowException(final String message) {
        super(message);
    }
}
