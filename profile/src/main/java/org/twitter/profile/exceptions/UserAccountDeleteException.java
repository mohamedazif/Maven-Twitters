package org.twitter.profile.exceptions;

/**
 * Custom exception for handling exceptions while user account deletion.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public class UserAccountDeleteException extends RuntimeException {
    public UserAccountDeleteException(final String message) {
        super(message);
    }
}
