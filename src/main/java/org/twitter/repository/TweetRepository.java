package org.twitter.repository;

import org.twitter.model.Tweet;
import org.twitter.model.User;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Repository for storing and retrieving tweets.
 * Uses in-memory storage for all tweets in the application.
 *
 * @version                     1.0 15 Oct 2025
 * @author                      Mohamed Abdul Azif
 */
public final class TweetRepository {

    private static final Collection<Tweet> ALL_TWEETS = new ArrayList<>();

    private TweetRepository() { }

    /**
     * Adds a tweet to the global list of tweets.
     *
     * @param tweet the tweet to be added
     */
    public static boolean addTweet(final Tweet tweet) {
        return ALL_TWEETS.add(tweet);
    }

    /**
     * Retrieves all tweets posted by a specific user.
     *
     * @param userId    The ID of the user
     * @return          A collection of tweets by the user
     */
    public static Collection<Tweet> getSpecificUserTweets(final String userId) {
        return ALL_TWEETS.stream()
                .filter(tweet -> userId.equals(tweet.getUserId()))
                .toList();
    }

    /**
     * Retrieves all tweets for the logged-in user's timeline.
     * This includes tweets posted by users they follow.
     *
     * @param user      The logged-in user
     * @return          A collection of timeline tweets
     */
    public static Collection<Tweet> getTimelineTweets(final User user) {
        final Collection<Tweet> timelineTweets = new ArrayList<>();

        for (final Tweet tweet : ALL_TWEETS) {
            if (user.getFollowing().contains(tweet.getUserId())) {
                timelineTweets.add(tweet);
            }
        }

        return timelineTweets;
    }
}