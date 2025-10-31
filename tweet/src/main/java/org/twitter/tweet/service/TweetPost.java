package org.twitter.tweet.service;

import org.twitter.tweet.model.Tweet;
import org.twitter.tweet.repository.TweetDBRepo;

public class TweetPost implements TweetService {

    @Override
    public boolean postTweet(final String userId, final String tweetContent) {
        return TweetDBRepo.addTweet(new Tweet(userId, tweetContent));
    }
}
