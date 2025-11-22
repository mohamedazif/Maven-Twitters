package tweet.controller;

import org.twitter.tweet.model.Tweet;
import org.twitter.tweet.service.TweetService;

/**
 * To coordinate the control between view and TweetService.
 *
 * @version             1.0
 * @author              Mohamed Abdul Azif
 */
public final class TweetController {

    private final TweetService tweetService;

    public TweetController(final TweetService tweetService) {
        this.tweetService = tweetService;
    }

    /**
     * To post a tweet.
     *
     * @param userId        User-ID of the logged-in user
     * @param tweetContent  Content of the tweet to post
     * @return              Whether tweet posted or not
     */
    public boolean postTweet(final long userId, final String tweetContent) {
        return tweetService.post(new Tweet(userId, tweetContent));
    }
}
