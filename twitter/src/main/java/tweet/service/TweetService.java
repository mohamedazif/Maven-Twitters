package tweet.service;

import org.twitter.tweet.model.Tweet;

/**
 * Interface for providing tweet related services.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public interface TweetService {

    boolean post(final Tweet tweet);

    boolean delete(final int tweetId);
}
