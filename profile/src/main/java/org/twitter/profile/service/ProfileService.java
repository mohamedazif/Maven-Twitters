package org.twitter.profile.service;

import org.twitter.timeline.model.TweetsAndRetweets;

import java.util.Collection;

/**
 * Interface which provides abstraction for providing Profile related services.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public interface ProfileService {

    int getFollowingCount(final long userId);

    int getFollowersCount(final long userId);

    Collection<TweetsAndRetweets> getTweetsAndRetweets(final long userId);

    boolean deleteTweet(final int tweetId);
}
