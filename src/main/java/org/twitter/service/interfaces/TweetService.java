package org.twitter.service.interfaces;

public interface TweetService {
    boolean postTweet(String userId, String tweetContent);
}
