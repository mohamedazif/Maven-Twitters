package timeline.service;

import org.twitter.timeline.model.TweetsAndRetweets;
import org.twitter.timeline.repository.TimelineRepository;
import org.twitter.timeline.service.TimelineService;

import java.util.Collection;

/**
 * Service class which interacts with the repository to extract timeline for a user.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public final class TimelineServiceImpl implements TimelineService {
    private final TimelineRepository repository;

    public TimelineServiceImpl(TimelineRepository timelineRepository) {
        this.repository = timelineRepository;
    }
    /**
     * To get the timeline feed for a user.
     *
     * @param userId        Logged-in User-ID
     * @return              Collection of tweets with their details
     */
    @Override
    public Collection<TweetsAndRetweets> getTimelineTweets(final long userId) {
        return repository.getTimelineTweets(userId);
    }

    /**
     * To like a tweet.
     *
     * @param tweetId       ID of the tweet to like
     * @param userId        Logged-in User-ID
     * @return              True if like success else false
     */
    @Override
    public boolean likeTweet(final int tweetId, final long userId) {
        return repository.like(tweetId, userId);
    }

    /**
     * To check if a tweet is liked by a user.
     *
     * @param tweetId       Tweet-ID of the tweet to check
     * @param userId        Logged-in User-ID
     * @return              True if liked by the user else false
     */
    @Override
    public boolean isLikedBy(final int tweetId, final long userId) {
        return repository.hasTweetLiked(tweetId, userId);
    }

    /**
     * To retweet a tweet.
     *
     * @param tweetId       ID of the tweet to retweet
     * @param userId        Logged-in User-ID
     * @return              True if retweeted else false
     */
    @Override
    public boolean retweet(final int tweetId, final long userId) {
        return repository.retweet(userId, tweetId);
    }

    /**
     * To check if a tweet is retweeted by a user.
     *
     * @param tweetId       Tweet-ID of the tweet to check
     * @param userId        Logged-in User-ID
     * @return              True if retweeted by the user else false
     */
    @Override
    public boolean isRetweetedBy(final int tweetId, final long userId) {
        return repository.hasRetweeted(tweetId, userId);
    }

    /**
     * To get the tweets and retweets of a user.
     *
     * @param userId        Logged-in User-ID
     * @return              Collection of tweets with their details
     */
    @Override
    public Collection<TweetsAndRetweets> getTweetsAndRetweets(final long userId) {
        return repository.getTweetsAndRetweets(userId);
    }
}