package org.twitter.service;

import org.twitter.model.Retweet;
import org.twitter.model.Tweet;
import org.twitter.model.User;
import org.twitter.repository.TweetRepository;
import org.twitter.service.interfaces.TimelineService;

import java.util.Collection;

/**
 * Displays the tweets of the users followed by the logged-in user.
 *
 * @version             1.0 15 Oct 2025
 * @author              Mohamed Abdul Azif
 */
public final class UserTimeline implements TimelineService {

    /**
     * To get Timeline tweets for the User.
     *
     * @param user      Logged-In user
     * @return          Collection of Timeline tweets
     */
    @Override
    public Collection<Tweet> getTimelineTweets(final User user) {
        return TweetRepository.getTimelineTweets(user);
    }

    /**
     * To like a tweet.
     *
     * @param user      Logged-in user
     * @param tweet     Tweet to be liked
     * @return          Whether liked or not
     */
    @Override
    public boolean likeTweet(final User user, final Tweet tweet) {

        if (tweet.getLikedBy().contains(user.getUserId())) {
            System.err.println("Tweet already liked!");
            return false;
        }

        tweet.addLikedBy(user.getUserId());
        return true;
    }

    /**
     * To retweet a tweet.
     *
     * @param user      Logged-in user
     * @param tweet     Tweet to be retweeted
     * @return          Whether retweeted or not
     */
    @Override
    public boolean retweet(final User user, final Tweet tweet) {

        if (tweet.isRetweetedBy(user.getUserId())) {
            System.err.println("Tweet retweeted already!");
            return false;
        }

        tweet.addRetweeter(user.getUserId());
        user.getRetweets().add(new Retweet(tweet, user.getUserId()));
        return true;
    }
}
