package org.twitter.service;

import org.twitter.model.Retweet;
import org.twitter.model.Tweet;
import org.twitter.model.User;
import org.twitter.repository.TweetRepository;
import org.twitter.service.interfaces.ProfileService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Shows the Profile of the user with following & followers count and tweets.
 *
 * @version                 1.0 15 Oct 2025
 * @author                  Mohamed Abdul Azif
 */
public final class UserProfile implements ProfileService {

    /**
     * To get tweets and retweets of the User.
     *
     * @param user  Logged-in User
     * @return      List containing tweets and retweets of the user
     */
    @Override
    public List<Object> getUserProfileTweets(final User user) {
        List<CombinedTweets> ogNrtTweets = new ArrayList<>();

        // Original Tweets
        for (Tweet tweet : TweetRepository.getSpecificUserTweets(user.getUserId())) {
            ogNrtTweets.add(new CombinedTweets(tweet.getCreatedAt(), tweet, false));
        }

        // Retweets
        for (Retweet retweet : user.getRetweets()) {
            ogNrtTweets.add(new CombinedTweets(retweet.getRetweetedAt(), retweet.getOgTweet(), true));
        }

        ogNrtTweets.sort(Comparator.comparing(CombinedTweets::timeStamp).reversed());
        return new ArrayList<>(ogNrtTweets);
    }

    /**
     * Local DTO for combining tweets + retweets.
     */
    public record CombinedTweets(LocalDateTime timeStamp, Tweet tweet, boolean isRetweet) { }
}
