package tweet.exceptions;

public class TweetNotPostedException extends RuntimeException {
    public TweetNotPostedException(final String message) {
        super(message);
    }
}
