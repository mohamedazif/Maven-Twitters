package tweet.service;

import org.twitter.tweet.model.Tweet;
import org.twitter.tweet.repository.TweetRepository;

/**
 * Post a tweet by a user.
 *
 * @version         1.0
 * @author          Mohamed Azif
 */
public class TweetServiceImpl implements TweetService {
    private final TweetRepository repository;

    public TweetServiceImpl(TweetRepository tweetRepository) {
        this.repository = tweetRepository;
    }
    /**
     * To post a tweet.
     *
     * @param tweet             Tweet object with content and user-id
     * @return                  True if posted else false
     */
    @Override
    public boolean post(final Tweet tweet) {
        return repository.post(tweet);
    }

    /**
     * To delete a tweet.
     *
     * @param tweetId           ID of the tweet to delete
     * @return                  True if deleted else false
     */
    @Override
    public boolean delete(final int tweetId) {
        return repository.delete(tweetId);
    }
}
