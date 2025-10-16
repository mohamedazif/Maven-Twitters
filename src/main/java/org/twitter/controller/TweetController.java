package org.twitter.controller;

import org.twitter.service.ServiceFactory;
import org.twitter.service.interfaces.TweetService;

/**
 * To coordinate the control between view and TweetService.
 *
 * @version             1.0 15-Oct-2025
 * @author              Mohamed Abdul Azif
 */
public final class TweetController {

    private final TweetService tweetService;

    public TweetController() {
        this.tweetService = ServiceFactory.getInstance().getTweetService();
    }

    /**
     * To post a tweet.
     *
     * @param userId        User-ID of the logged-in user
     * @param tweetContent  Content of the tweet to post
     * @return              Whether tweet posted or not
     */
    public boolean postTweet(final String userId, final String tweetContent) {
        return tweetService.postTweet(userId, tweetContent);
    }
}
