package org.twitter.tweet.service;

public interface TweetService {
    boolean postTweet(String userId, String tweetContent);
}
