package timeline.repository;

import org.twitter.timeline.model.TweetsAndRetweets;

import java.util.Collection;

/**
 * Interface providing abstraction between TimelineService and Repository.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public interface TimelineRepository {

    Collection<TweetsAndRetweets> getTweetsAndRetweets(final long userId);

    Collection<TweetsAndRetweets> getTimelineTweets(final long userId);

    boolean hasTweetLiked(final int tweetId, final long userId);

    boolean retweet(final long retweetBy, final int tweetId);

    boolean like(final int tweetId, final long likedBy);

    boolean hasRetweeted(final int tweetId, final long userId);
}
