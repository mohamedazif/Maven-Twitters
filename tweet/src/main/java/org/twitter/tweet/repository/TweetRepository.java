package org.twitter.tweet.repository;

import org.twitter.tweet.model.Tweet;

/**
 * Interface providing abstraction between TweetService and TweetRepository.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public interface TweetRepository {

    boolean post(final Tweet tweet);

    boolean delete(final int tweetId);
}
