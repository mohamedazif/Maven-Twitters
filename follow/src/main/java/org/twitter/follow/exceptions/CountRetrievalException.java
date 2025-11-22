package org.twitter.follow.exceptions;

/**
 * Custom Exception for throwing count retrieval time exception.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public class CountRetrievalException extends RuntimeException {
    public CountRetrievalException(final String message) {
        super(message);
    }
}
