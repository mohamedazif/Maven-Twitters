package org.twitter.profile.exceptions;

/**
 * Custom exception for handling exceptions while edit the user's profile.
 *
 * @version                 1.0
 * @author                  Mohamed Azif
 */
public class UserProfileEditException extends RuntimeException {
    public UserProfileEditException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
