package org.twitter.service;

import org.twitter.model.Tweet;
import org.twitter.repository.TweetRepository;
import org.twitter.service.interfaces.TweetService;

/**
 * Post tweets created by the user.
 *
 * @version             1.0 15-Oct-2025
 * @author              Mohamed Abdul Azif
 */
public class TweetPost implements TweetService {

    /**
     * Allows user to post a tweet.
     *
     * @param userId        UserID of th logged-in user
     * @param tweetContent  Content of the tweet
     * @return              Whether tweet posted or not
     */
    @Override
    public boolean postTweet(final String userId, final String tweetContent) {
        final Tweet tweet = new Tweet(userId, tweetContent);
        return TweetRepository.addTweet(tweet);
    }
}
