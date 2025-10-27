package org.twitter.repository;

import org.twitter.model.Retweet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repository for storing retweet information.
 *
 * @version         1.0
 * @author          Mohamed Azif
 */
public final class RetweetRepository {

    private static final Map<String, List<Retweet>> RETWEETS = new HashMap<>();

    private RetweetRepository() { }

    /**
     * To save the retweet of the user.
     *
     * @param userId        Logged-in user
     * @param retweet       Tweet retweeted by the user
     */
    public static void saveRetweet(String userId, Retweet retweet) {
        RETWEETS.computeIfAbsent(userId, k -> new ArrayList<>())
                .add(retweet);
    }

    /**
     * To get specific user retweets.
     *
     * @param userId        Logged-in user
     * @return              Specific user Retweet list
     */
    public static List<Retweet> getUserRetweets(String userId) {
        return RETWEETS.getOrDefault(userId, new ArrayList<>());
    }

//    public static boolean hasRetweeted(String userId, int tweetId) {
//        return RETWEETS.getOrDefault(userId, new ArrayList<>())
//                .stream()
//                .anyMatch(r -> r.getOgTweet().getTweetId() == tweetId);
//    }
}
