package org.twitter.timeline.exceptions;

public class TweetNotRetweetedException extends RuntimeException {
    public TweetNotRetweetedException(final String message) {
        super(message);
    }
}
