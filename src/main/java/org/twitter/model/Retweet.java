package org.twitter.model;

import java.time.LocalDateTime;

/**
 * Gathers info about Retweeted tweets.
 *
 * @version         1.0 15-Oct-2025
 * @author          Mohamed Abdul Azif
 */
public final class Retweet extends Tweet {
    private final String  retweetedUserId;
    private final Tweet ogTweet;
    private final LocalDateTime retweetedAt;

    /**
     * Constructor for the Retweet class.
     * @param ogTweet           Tweet object which was retweeted
     * @param retweetedUserId   User-ID of the user who retweeted the tweet
     */
    public Retweet(final Tweet ogTweet, final String retweetedUserId) {
        super(ogTweet.getUserId(), ogTweet.getTweetContent());
        this.ogTweet = ogTweet;
        this.retweetedUserId = retweetedUserId;
        this.retweetedAt = LocalDateTime.now();
    }

    public String getRetweetedUserId() {
        return retweetedUserId;
    }

    public Tweet getOgTweet() {
        return ogTweet;
    }

    public LocalDateTime getRetweetedAt() {
        return retweetedAt;
    }
}
