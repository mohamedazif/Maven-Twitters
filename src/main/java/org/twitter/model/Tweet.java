package org.twitter.model;


import org.twitter.service.Utility;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

/**
 * Represents a Tweet posted by a user.
 * Contains tweet content, author information, and counts for
 * likes and retweets.
 *
 * @version                     1.0 15 Oct 2025
 * @author                      Mohamed Abdul Azif
 */
public class Tweet {

    private final Collection<String> likedBy = new HashSet<>();
    private final Collection<String> retweetedBy = new HashSet<>();
    private final int tweetId;
    private final String userId;
    private final String tweetContent;
    private final LocalDateTime createdAt;

    /**
     * Creates a new Tweet with a unique ID.
     *
     * @param userId        the ID of the user who posted the tweet
     * @param tweetContent  the content of the tweet
     */
    public Tweet(final String userId, final String tweetContent) {
        this.tweetId = Utility.nextTweetId();
        this.userId = userId;
        this.tweetContent = tweetContent;
        this.createdAt = LocalDateTime.now();
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

    public Collection<String> getLikedBy() {
        return likedBy;
    }

    public Collection<String> getRetweetedBy() {
        return retweetedBy;
    }

    public void addLikedBy(final String userId) {
        likedBy.add(userId);
    }

    public boolean isRetweetedBy(String userId) {
        return retweetedBy.contains(userId);
    }

    public void addRetweeter(String userId) {
        retweetedBy.add(userId);
    }
}
