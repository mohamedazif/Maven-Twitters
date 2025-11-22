package timeline.exceptions;

public class TweetNotLikedException extends RuntimeException {

    public TweetNotLikedException(final String message) {
        super(message);
    }
}
