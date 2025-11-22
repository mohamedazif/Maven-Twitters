package tweet.exceptions;

public class TweetNotDeletedException extends RuntimeException {
    public TweetNotDeletedException(final String message) {
        super(message);
    }
}
