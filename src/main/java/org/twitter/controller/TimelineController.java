package org.twitter.controller;

import org.twitter.model.Tweet;
import org.twitter.model.User;
import org.twitter.service.ServiceFactory;
import org.twitter.service.interfaces.TimelineService;

import java.util.Collection;

/**
 * To coordinate the control between view and TimelineService.
 *
 * @version             1.0 15-Oct-2025
 * @author              Mohamed Abdul Azif
 */
public final class TimelineController {

    private final TimelineService timelineService;

    public TimelineController() {
        this.timelineService = ServiceFactory.getInstance().getTimeLineService();
    }

    /**
     * To get Timeline tweets for the User.
     *
     * @param user  Logged-In user
     * @return      Collection of Timeline tweets
     */
    public Collection<Tweet> getTimelineTweets(final User user) {
        return timelineService.getTimelineTweets(user);
    }

    /**
     * To like a tweet.
     *
     * @param user      Logged-in user
     * @param tweet     Tweet to be liked
     * @return          Whether liked or not
     */
    public boolean likeTweet(final User user, final Tweet tweet) {
        return timelineService.likeTweet(user, tweet);
    }

    /**
     * To retweet a tweet.
     *
     * @param user      Logged-in user
     * @param tweet     Tweet to be retweeted
     * @return          Whether retweeted or not
     */
    public boolean retweet(final User user, final Tweet tweet) {
        return timelineService.retweet(user, tweet);
    }
}
