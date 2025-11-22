package org.twitter.timeline.controller;

import org.twitter.timeline.model.TweetsAndRetweets;
import org.twitter.timeline.service.TimelineService;

import java.util.Collection;

/**
 * Coordinates the control between TimelineView and TimelineService.
 *
 * @version             1.0
 * @author              Mohamed Azif
 */
public final class TimelineController {

    private final TimelineService timelineService;

    public TimelineController(final TimelineService timelineService) {
        this.timelineService = timelineService;
    }

    /**
     * To get the timeline feed for a user.
     *
     * @param userId        Logged-in User-ID
     * @return              Collection of tweets with their details
     */
    public Collection<TweetsAndRetweets> getTimelineTweets(final long userId) {
        return timelineService.getTimelineTweets(userId);
    }

    /**
     * To like a tweet.
     *
     * @param tweetId       ID of the tweet to like
     * @param userId        Logged-in User-ID
     * @return              True if like success else false
     */
    public boolean likeTweet(final int tweetId, final long userId) {
        return timelineService.likeTweet(tweetId, userId);
    }

    /**
     * To check if a tweet is liked by a user.
     *
     * @param tweetId       Tweet-ID of the tweet to check
     * @param userId        Logged-in User-ID
     * @return              True if liked by the user else false
     */
    public boolean isTweetLikedBy(final int tweetId, final long userId) {
        return timelineService.isLikedBy(tweetId, userId);
    }

    /**
     * To check if a tweet is retweeted by a user.
     *
     * @param tweetId       Tweet-ID of the tweet to check
     * @param userId        Logged-in User-ID
     * @return              True if retweeted by the user else false
     */
    public boolean isRetweetedBy(final int tweetId, final long userId) {
        return timelineService.isRetweetedBy(tweetId,userId);
    }

    /**
     * To retweet a tweet.
     *
     * @param tweetId       ID of the tweet to retweet
     * @param userId        Logged-in User-ID
     * @return              True if retweeted else false
     */
    public boolean retweet(final int tweetId, final long userId) {
        return timelineService.retweet(tweetId, userId);
    }
}
