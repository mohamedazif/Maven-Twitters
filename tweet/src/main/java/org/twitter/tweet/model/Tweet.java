package org.twitter.tweet.model;

import java.time.LocalDateTime;

/**
 * Represents a Tweet posted by a user.
 * Contains tweet content, author information.
 *
 * @version                     1.0
 * @author                      Mohamed Abdul Azif
 */
public class Tweet {

    private final int tweetId;
    private final String userId;
    private final String tweetContent;
    private final LocalDateTime createdAt;

    /**
     * Creates a new Tweet object.
     *
     * @param userId        the ID of the user who posted the tweet
     * @param tweetContent  the content of the tweet
     */
    public Tweet(final String userId, final String tweetContent) {
        this.tweetId = 0;
        this.userId = userId;
        this.tweetContent = tweetContent;
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Creates a new Tweet object from DB
     *
     * @param tweetId       ID of the Tweet
     * @param userId        ID of the User who posted the tweet
     * @param tweetContent  Content of the tweet
     * @param createdAt     Time the tweet got posted.
     */
    public Tweet(final int tweetId, final String userId,
                 final String tweetContent, final LocalDateTime createdAt) {
        this.tweetId = tweetId;
        this.userId = userId;
        this.tweetContent = tweetContent;
        this.createdAt = createdAt;
    }

    public int getTweetId() {
        return tweetId;
    }

    public String getUserId() {
        return userId;
    }

    public String getTweetContent() {
        return tweetContent;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
