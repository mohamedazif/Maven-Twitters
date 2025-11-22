package timeline.service;

import org.twitter.timeline.model.TweetsAndRetweets;

import java.util.Collection;

/**
 * Interface for providing timeline related services.
 *
 * @version                 1.0
 * @author                  Mohamed Azif
 */
public interface TimelineService {

    Collection<TweetsAndRetweets> getTimelineTweets(final long userId);

    Collection<TweetsAndRetweets> getTweetsAndRetweets(final long userId);

    boolean likeTweet(final int tweetId, final long userId);

    boolean isLikedBy(final int tweetId, final long userId);

    boolean isRetweetedBy(final int tweetId, final long userId);

    boolean retweet(final int tweetId, final long userId);
}
